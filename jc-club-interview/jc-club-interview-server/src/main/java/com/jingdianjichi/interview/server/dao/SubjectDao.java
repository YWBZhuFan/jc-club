package com.jingdianjichi.interview.server.dao;

import com.jingdianjichi.interview.server.entity.po.SubjectCategory;
import com.jingdianjichi.interview.server.entity.po.SubjectInfo;
import com.jingdianjichi.interview.server.entity.po.SubjectLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SubjectDao {

    List<SubjectLabel> listAllLabel();

    List<SubjectCategory> listAllCategory();

    List<SubjectInfo> listSubjectByLabelIds(@Param("ids") List<Long> ids);
}

