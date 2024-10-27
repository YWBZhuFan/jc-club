package com.jichi.subject.application.controller;

import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.infra.basic.entity.SubjectInfoEs;
import com.jichi.subject.infra.basic.service.SubjectEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhuFan
 * @data 2024/10/15/015 0:42
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class TestController {

    @Resource
    private SubjectEsService subjectEsService;

/*    @PostMapping("/querySubjectByKeyword")
    public void querySubjectByKeyword() {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord("mysql");
        PageResult<SubjectInfoEs> subjectInfoEsPageResult = subjectEsService.querySubjectList(subjectInfoEs);
        log.info("subjectInfoEsPageResult:{}", subjectInfoEsPageResult);
    }*/
}
