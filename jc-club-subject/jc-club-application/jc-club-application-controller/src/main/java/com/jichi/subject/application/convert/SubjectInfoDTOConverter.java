package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.application.dto.SubjectInfoDTO;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOtoBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBOToDTO(SubjectInfoBO boResult);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);

    List<SubjectInfoDTO> convertBOToDTOList(List<SubjectInfoBO> boList);
}