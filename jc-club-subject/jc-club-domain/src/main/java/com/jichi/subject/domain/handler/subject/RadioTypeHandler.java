package com.jichi.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import com.jichi.subject.common.enums.IsDeletedFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domain.convert.RadioSubjectConverter;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectRadio;
import com.jichi.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选题的策略类
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource private SubjectRadioService subjectRadioService;

    /**
     * 枚举身份的识别
     *
     * @return
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    /**
     * 实际题目新增
     *
     * @param subjectInfoBO
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题的插入
        LinkedList<SubjectRadio> subjectRadioList = new LinkedList<>();
        Preconditions.checkNotNull(subjectInfoBO.getOptionList(), "答案不能为空");
        subjectInfoBO.getOptionList().forEach(answer -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(answer);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectId));
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = RadioSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
