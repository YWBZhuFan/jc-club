package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectAnswerDTO;
import com.jichi.subject.application.dto.SubjectInfoDTO;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    List<SubjectAnswerBO> convertListDTOtoBO(List<SubjectAnswerDTO> subjectAnswerDTO);
}