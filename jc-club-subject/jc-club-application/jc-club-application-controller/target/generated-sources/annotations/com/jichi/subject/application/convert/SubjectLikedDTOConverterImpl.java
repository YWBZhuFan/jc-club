package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectLikedDTO;
import com.jichi.subject.domain.entity.SubjectLikedBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T18:05:06+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (Amazon.com Inc.)"
)
public class SubjectLikedDTOConverterImpl implements SubjectLikedDTOConverter {

    @Override
    public SubjectLikedBO convertDTOToBO(SubjectLikedDTO subjectLikedDTO) {
        if ( subjectLikedDTO == null ) {
            return null;
        }

        SubjectLikedBO subjectLikedBO = new SubjectLikedBO();

        subjectLikedBO.setPageNo( subjectLikedDTO.getPageNo() );
        subjectLikedBO.setPageSize( subjectLikedDTO.getPageSize() );
        subjectLikedBO.setId( subjectLikedDTO.getId() );
        subjectLikedBO.setSubjectId( subjectLikedDTO.getSubjectId() );
        subjectLikedBO.setLikeUserId( subjectLikedDTO.getLikeUserId() );
        subjectLikedBO.setStatus( subjectLikedDTO.getStatus() );
        subjectLikedBO.setCreatedBy( subjectLikedDTO.getCreatedBy() );
        subjectLikedBO.setCreatedTime( subjectLikedDTO.getCreatedTime() );
        subjectLikedBO.setUpdateBy( subjectLikedDTO.getUpdateBy() );
        subjectLikedBO.setUpdateTime( subjectLikedDTO.getUpdateTime() );
        subjectLikedBO.setIsDeleted( subjectLikedDTO.getIsDeleted() );

        return subjectLikedBO;
    }
}
