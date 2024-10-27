package com.jichi.subject.domain.convert;

import com.jichi.subject.domain.entity.SubjectCategoryBO;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConverter {

    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory convertBotoCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertBoToCategoryList(List<SubjectCategory> CategoryList);

    List<SubjectCategoryBO> convertBoToCategory(List<SubjectCategory> subjectCategoryList);
}
