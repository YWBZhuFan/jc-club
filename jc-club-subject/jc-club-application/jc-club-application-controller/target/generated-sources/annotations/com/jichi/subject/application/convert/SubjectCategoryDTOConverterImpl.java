package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.domain.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T18:05:06+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (Amazon.com Inc.)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertBotoCategory(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBO.size() );
        for ( SubjectCategoryBO subjectCategoryBO1 : subjectCategoryBO ) {
            list.add( convertBotoCategoryDTO( subjectCategoryBO1 ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryBO convertDtotoCategoryBO(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public SubjectCategoryBO convertDtoToCategoryBO(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public SubjectCategoryDTO convertBotoCategoryDTO(SubjectCategoryBO bo) {
        if ( bo == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( bo.getId() );
        subjectCategoryDTO.setCategoryName( bo.getCategoryName() );
        subjectCategoryDTO.setCategoryType( bo.getCategoryType() );
        subjectCategoryDTO.setImageUrl( bo.getImageUrl() );
        subjectCategoryDTO.setParentId( bo.getParentId() );
        subjectCategoryDTO.setCount( bo.getCount() );

        return subjectCategoryDTO;
    }
}
