package com.jichi.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jichi.subject.application.convert.SubjectCategoryDTOConverter;
import com.jichi.subject.application.convert.SubjectLabelDTOConverter;
import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.application.dto.SubjectLabelDTO;
import com.jichi.subject.common.entity.Result;
import com.jichi.subject.common.util.LoginUtil;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import com.jichi.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 刷题分类Controller
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.add:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertBotoCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add error:{}", e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            String loginId = LoginUtil.getLoginId();
            log.info("SubjectCategoryController.queryPrimaryCategory:{}", loginId);
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

    /**
     * 查询分类及标签一次性
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryAndLabel.dto:{}"
                        , JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            List<SubjectCategoryDTO> dtoList = new LinkedList<>();
            subjectCategoryBOList.forEach(bo ->{
                SubjectCategoryDTO dto = SubjectCategoryDTOConverter.INSTANCE.convertBotoCategoryDTO(bo);
                List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBOToLabelDTOList(bo.getLabelBOList());
                dto.setLabelDTOList(labelDTOList);
                dtoList.add(dto);
            });

            return Result.ok(dtoList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }
    }
}
