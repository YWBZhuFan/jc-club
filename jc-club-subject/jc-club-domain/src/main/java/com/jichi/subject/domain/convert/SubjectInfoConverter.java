package com.jichi.subject.domain.convert;

import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOtoInfo(SubjectInfoBO subjectInfoBO);
    List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList);
    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);

    SubjectInfoBO convertInfoToBO(SubjectInfoBO infoBO);
}
