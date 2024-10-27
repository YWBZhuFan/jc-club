package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertBotoCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoToCategoryDTOList (List<SubjectCategoryBO> subjectCategoryBO);

    SubjectCategoryBO convertDtotoCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryBO convertDtoToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryDTO convertBotoCategoryDTO(SubjectCategoryBO bo);
}