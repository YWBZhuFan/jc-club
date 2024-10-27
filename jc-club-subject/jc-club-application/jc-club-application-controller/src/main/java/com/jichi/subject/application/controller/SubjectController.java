package com.jichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jichi.subject.application.convert.SubjectAnswerDTOConverter;
import com.jichi.subject.application.convert.SubjectCategoryDTOConverter;
import com.jichi.subject.application.convert.SubjectInfoDTOConverter;
import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.application.dto.SubjectInfoDTO;
import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.common.entity.Result;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.service.SubjectCategoryDomainService;
import com.jichi.subject.domain.service.SubjectInfoDomainService;
import com.jichi.subject.infra.basic.entity.SubjectInfoEs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource private SubjectInfoDomainService subjectInfoDomainService;
    @Resource private SubjectCategoryDomainService subjectCategoryDomainService;
    @Resource private RocketMQTemplate rocketMQTemplate;

    /**
     * 新增题目
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.add:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectName(), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds())
                    , "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds())
                    , "标签id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOtoBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertListDTOtoBO(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectController.add error:{}", e.getMessage(),e);
            return Result.fail("新增题目失败");
        }
    }

    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtotoCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
                    convertBoToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory error:{}", e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryByPrimary:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtotoCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOS = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOS = SubjectCategoryDTOConverter.INSTANCE
                    .convertBoToCategoryDTOList(subjectCategoryBOS);
            return Result.ok(subjectCategoryDTOS);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategoryByPrimary error:{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryByPrimary:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtotoCategoryBO(subjectCategoryDTO);
            Boolean isSuccess = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(isSuccess);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update error:{}", e.getMessage(), e);
            throw new RuntimeException("更新分类失败");
        }
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtotoCategoryBO(subjectCategoryDTO);
            Boolean isSuccess = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(isSuccess);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete error:{}", e.getMessage(), e);
            throw new RuntimeException("删除分类失败");
        }
    }

    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.getSubjectPage:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOtoBO(subjectInfoDTO);
            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(boPageResult);
        } catch (Exception e) {
            log.error("SubjectController.getSubjectPage error:{}", e.getMessage(),e);
            return Result.fail("分页查询题目列表失败");
        }
    }

    @PostMapping("/querySubjectInfo")
    public Result<PageResult<SubjectInfoDTO>> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.querySubjectInfo:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOtoBO(subjectInfoDTO);
            SubjectInfoBO boResult = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO dto = SubjectInfoDTOConverter.INSTANCE.convertBOToDTO(boResult);
            return Result.ok(dto);
        } catch (Exception e) {
            log.error("SubjectController.querySubjectInfo error:{}", e.getMessage(),e);
            return Result.fail("查询题目详情失败");
        }
    }

    @PostMapping("/getSubjectPageBySearch")
    public Result<PageResult<SubjectInfoEs>> getSubjectPageBySearch(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPageBySearch.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectInfoDTO.getKeyWord()), "关键词不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOToBO(subjectInfoDTO);
            subjectInfoBO.setPageNo(subjectInfoDTO.getPageNo());
            subjectInfoBO.setPageSize(subjectInfoDTO.getPageSize());
            PageResult<SubjectInfoEs> boPageResult = subjectInfoDomainService.getSubjectPageBySearch(subjectInfoBO);
            return Result.ok(boPageResult);
        } catch (Exception e) {
            log.error("SubjectCategoryController.getSubjectPageBySearch.error:{}", e.getMessage(), e);
            return Result.fail("全文检索失败");
        }
    }

    @PostMapping("/getContributeList")
    public Result<List<SubjectInfoDTO>> getContributeList() {
        try {
            List<SubjectInfoBO> boList = subjectInfoDomainService.getContributeList();
            List<SubjectInfoDTO> dtoList = SubjectInfoDTOConverter.INSTANCE.convertBOToDTOList(boList);
            return Result.ok(dtoList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.getContributeList.error:{}", e.getMessage(), e);
            return Result.fail("获取贡献榜失败");
        }
    }
}
