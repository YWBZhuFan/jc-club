package com.jichi.subject.domain.service;



import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectLabelBO;
import com.jichi.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * 题目信息领域服务
 * 
 * @author: ChickenWing
 * @date: 2023/10/3
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> getContributeList();
}
