package com.jichi.subject.domain.handler.subject;

import com.jichi.subject.common.enums.IsDeletedFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domain.convert.MultipleSubjectConverter;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectMultiple;
import com.jichi.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource private SubjectMultipleService subjectMultipleService;

    /**
     * 枚举身份的识别
     *
     * @return
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    /**
     * 实际题目新增
     *
     * @param subjectInfoBO
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultipleList);

    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(Long.valueOf(subjectId));
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
