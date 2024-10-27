package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectAnswerDTO;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T18:05:06+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (Amazon.com Inc.)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public List<SubjectAnswerBO> convertListDTOtoBO(List<SubjectAnswerDTO> subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectAnswerDTO.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO1 : subjectAnswerDTO ) {
            list.add( subjectAnswerDTOToSubjectAnswerBO( subjectAnswerDTO1 ) );
        }

        return list;
    }

    protected SubjectAnswerBO subjectAnswerDTOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }
}
