package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectAnswerDTO;
import com.jichi.subject.application.dto.SubjectInfoDTO;
import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T18:05:06+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (Amazon.com Inc.)"
)
public class SubjectInfoDTOConverterImpl implements SubjectInfoDTOConverter {

    @Override
    public SubjectInfoBO convertDTOtoBO(SubjectInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setPageNo( subjectInfoDTO.getPageNo() );
        subjectInfoBO.setPageSize( subjectInfoDTO.getPageSize() );
        subjectInfoBO.setId( subjectInfoDTO.getId() );
        subjectInfoBO.setSubjectName( subjectInfoDTO.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( subjectInfoDTO.getSubjectDifficult() );
        subjectInfoBO.setSettleName( subjectInfoDTO.getSettleName() );
        subjectInfoBO.setSubjectType( subjectInfoDTO.getSubjectType() );
        subjectInfoBO.setSubjectScore( subjectInfoDTO.getSubjectScore() );
        subjectInfoBO.setSubjectParse( subjectInfoDTO.getSubjectParse() );
        subjectInfoBO.setSubjectAnswer( subjectInfoDTO.getSubjectAnswer() );
        List<Integer> list = subjectInfoDTO.getCategoryIds();
        if ( list != null ) {
            subjectInfoBO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = subjectInfoDTO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoBO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        subjectInfoBO.setOptionList( subjectAnswerDTOListToSubjectAnswerBOList( subjectInfoDTO.getOptionList() ) );
        subjectInfoBO.setCategoryId( subjectInfoDTO.getCategoryId() );
        subjectInfoBO.setLabelId( subjectInfoDTO.getLabelId() );
        subjectInfoBO.setKeyWord( subjectInfoDTO.getKeyWord() );
        subjectInfoBO.setLiked( subjectInfoDTO.getLiked() );
        subjectInfoBO.setLikedCount( subjectInfoDTO.getLikedCount() );
        subjectInfoBO.setNextSubjectId( subjectInfoDTO.getNextSubjectId() );
        subjectInfoBO.setLastSubjectId( subjectInfoDTO.getLastSubjectId() );

        return subjectInfoBO;
    }

    @Override
    public SubjectInfoDTO convertBOToDTO(SubjectInfoBO boResult) {
        if ( boResult == null ) {
            return null;
        }

        SubjectInfoDTO subjectInfoDTO = new SubjectInfoDTO();

        subjectInfoDTO.setPageNo( boResult.getPageNo() );
        subjectInfoDTO.setPageSize( boResult.getPageSize() );
        subjectInfoDTO.setId( boResult.getId() );
        subjectInfoDTO.setSubjectName( boResult.getSubjectName() );
        subjectInfoDTO.setSubjectDifficult( boResult.getSubjectDifficult() );
        subjectInfoDTO.setSettleName( boResult.getSettleName() );
        subjectInfoDTO.setSubjectType( boResult.getSubjectType() );
        subjectInfoDTO.setSubjectScore( boResult.getSubjectScore() );
        subjectInfoDTO.setSubjectParse( boResult.getSubjectParse() );
        subjectInfoDTO.setSubjectAnswer( boResult.getSubjectAnswer() );
        List<Integer> list = boResult.getCategoryIds();
        if ( list != null ) {
            subjectInfoDTO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = boResult.getLabelIds();
        if ( list1 != null ) {
            subjectInfoDTO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        subjectInfoDTO.setOptionList( subjectAnswerBOListToSubjectAnswerDTOList( boResult.getOptionList() ) );
        subjectInfoDTO.setCategoryId( boResult.getCategoryId() );
        subjectInfoDTO.setLabelId( boResult.getLabelId() );
        subjectInfoDTO.setKeyWord( boResult.getKeyWord() );
        subjectInfoDTO.setLiked( boResult.getLiked() );
        subjectInfoDTO.setLikedCount( boResult.getLikedCount() );
        subjectInfoDTO.setNextSubjectId( boResult.getNextSubjectId() );
        subjectInfoDTO.setLastSubjectId( boResult.getLastSubjectId() );

        return subjectInfoDTO;
    }

    @Override
    public SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setPageNo( subjectInfoDTO.getPageNo() );
        subjectInfoBO.setPageSize( subjectInfoDTO.getPageSize() );
        subjectInfoBO.setId( subjectInfoDTO.getId() );
        subjectInfoBO.setSubjectName( subjectInfoDTO.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( subjectInfoDTO.getSubjectDifficult() );
        subjectInfoBO.setSettleName( subjectInfoDTO.getSettleName() );
        subjectInfoBO.setSubjectType( subjectInfoDTO.getSubjectType() );
        subjectInfoBO.setSubjectScore( subjectInfoDTO.getSubjectScore() );
        subjectInfoBO.setSubjectParse( subjectInfoDTO.getSubjectParse() );
        subjectInfoBO.setSubjectAnswer( subjectInfoDTO.getSubjectAnswer() );
        List<Integer> list = subjectInfoDTO.getCategoryIds();
        if ( list != null ) {
            subjectInfoBO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = subjectInfoDTO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoBO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        subjectInfoBO.setOptionList( subjectAnswerDTOListToSubjectAnswerBOList( subjectInfoDTO.getOptionList() ) );
        subjectInfoBO.setCategoryId( subjectInfoDTO.getCategoryId() );
        subjectInfoBO.setLabelId( subjectInfoDTO.getLabelId() );
        subjectInfoBO.setKeyWord( subjectInfoDTO.getKeyWord() );
        subjectInfoBO.setLiked( subjectInfoDTO.getLiked() );
        subjectInfoBO.setLikedCount( subjectInfoDTO.getLikedCount() );
        subjectInfoBO.setNextSubjectId( subjectInfoDTO.getNextSubjectId() );
        subjectInfoBO.setLastSubjectId( subjectInfoDTO.getLastSubjectId() );

        return subjectInfoBO;
    }

    @Override
    public List<SubjectInfoDTO> convertBOToDTOList(List<SubjectInfoBO> boList) {
        if ( boList == null ) {
            return null;
        }

        List<SubjectInfoDTO> list = new ArrayList<SubjectInfoDTO>( boList.size() );
        for ( SubjectInfoBO subjectInfoBO : boList ) {
            list.add( convertBOToDTO( subjectInfoBO ) );
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

    protected List<SubjectAnswerBO> subjectAnswerDTOListToSubjectAnswerBOList(List<SubjectAnswerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBO> list1 = new ArrayList<SubjectAnswerBO>( list.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : list ) {
            list1.add( subjectAnswerDTOToSubjectAnswerBO( subjectAnswerDTO ) );
        }

        return list1;
    }

    protected SubjectAnswerDTO subjectAnswerBOToSubjectAnswerDTO(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectAnswerDTO subjectAnswerDTO = new SubjectAnswerDTO();

        subjectAnswerDTO.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerDTO.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerDTO.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerDTO;
    }

    protected List<SubjectAnswerDTO> subjectAnswerBOListToSubjectAnswerDTOList(List<SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list1 = new ArrayList<SubjectAnswerDTO>( list.size() );
        for ( SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerDTO( subjectAnswerBO ) );
        }

        return list1;
    }
}
