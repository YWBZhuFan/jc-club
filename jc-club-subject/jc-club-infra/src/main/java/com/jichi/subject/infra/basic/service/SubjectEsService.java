package com.jichi.subject.infra.basic.service;

import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.infra.basic.entity.SubjectInfoEs;

/**
 * @author ZhuFan
 * @data 2024/10/14/014 21:40
 */
public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}
