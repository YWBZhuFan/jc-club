package com.jichi.subject.domain.handler.subject;

import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际题目新增
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    SubjectOptionBO query(int subjectId);
}
