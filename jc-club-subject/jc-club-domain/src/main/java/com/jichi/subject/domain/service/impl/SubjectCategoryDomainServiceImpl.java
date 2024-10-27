package com.jichi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jichi.subject.common.enums.IsDeletedFlagEnum;
import com.jichi.subject.domain.config.ThreadPoolConfig;
import com.jichi.subject.domain.convert.SubjectCategoryConverter;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import com.jichi.subject.domain.entity.SubjectLabelBO;
import com.jichi.subject.domain.service.SubjectCategoryDomainService;
import com.jichi.subject.domain.util.CacheUtil;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import com.jichi.subject.infra.basic.entity.SubjectLabel;
import com.jichi.subject.infra.basic.entity.SubjectMapping;
import com.jichi.subject.infra.basic.service.SubjectCategoryService;
import com.jichi.subject.infra.basic.service.SubjectLabelService;
import com.jichi.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private CacheUtil cacheUtil;
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private ThreadPoolExecutor labelThreadPool;




    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBotoCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }


    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBotoCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE.convertBoToCategoryList(subjectCategoryList);
        boList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return boList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBotoCategory(subjectCategoryBO);
        int result = subjectCategoryService.update(subjectCategory);
        return result > 0;
    }

    @SneakyThrows
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        Long id = subjectCategoryBO.getId();
        String cacheKey = "categoryAndLabel:" + subjectCategoryBO.getId();
        List<SubjectCategoryBO> subjectCategoryBOList = cacheUtil.getResult(cacheKey, SubjectCategoryBO.class, (key) -> getSubjectCategoryBOS(id));
        return subjectCategoryBOList;
    }

    private List<SubjectCategoryBO> getSubjectCategoryBOS(Long categoryId) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("queryCategoryAndLabel:{}", JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        //一次性获取标签信息
        HashMap<Long, List<SubjectLabelBO>> map = new HashMap<>();
        // 利用CompletableFuture实现多线程异步任务
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = categoryBOList.stream()
                .map(category ->
                        CompletableFuture.supplyAsync(() ->
                                getLabelBOList(category), labelThreadPool)).collect(Collectors.toList());
        // 利用FutureTask实现多线程异步任务
        /*List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new LinkedList<>();
        // 线程池并发调用
        categoryBOList.forEach(category -> {
            // 创建异步任务
            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() -> getLabelBOList(category));
            // 加入到异步任务集合
            futureTaskList.add(futureTask);
            // 提交到线程池中
            labelThreadPool.submit(futureTask);
        });*/
        completableFutureList.forEach(futureTask -> {
            try {
                Map<Long, List<SubjectLabelBO>> listMap = futureTask.get();
                map.putAll(listMap);
            } catch (Exception e) {
                log.error("getLabelBOList error:{}", e.getMessage());
            }
        });
        categoryBOList.forEach(category -> category.setLabelBOList(map.get(category.getId())));
        return categoryBOList;
    }

    private Map<Long, List<SubjectLabelBO>> getLabelBOList(SubjectCategoryBO category) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) return null;
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> labelBOList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(label.getCategoryId());
            bo.setSortNum(label.getSortNum());
            labelBOList.add(bo);
        });
        labelMap.put(category.getId(), labelBOList);
        return labelMap;
    }


    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBotoCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int delete = subjectCategoryService.update(subjectCategory);
        return delete > 0;
    }


}
