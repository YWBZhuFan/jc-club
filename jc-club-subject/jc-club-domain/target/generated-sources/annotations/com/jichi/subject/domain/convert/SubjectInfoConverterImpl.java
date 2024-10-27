package com.jichi.subject.domain.convert;

import com.jichi.subject.domain.entity.SubjectAnswerBO;
import com.jichi.subject.domain.entity.SubjectInfoBO;
import com.jichi.subject.domain.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T18:05:03+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (Amazon.com Inc.)"
)
public class SubjectInfoConverterImpl implements SubjectInfoConverter {

    @Override
    public SubjectInfo convertBOtoInfo(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectInfo subjectInfo = new SubjectInfo();

        subjectInfo.setId( subjectInfoBO.getId() );
        subjectInfo.setSubjectName( subjectInfoBO.getSubjectName() );
        subjectInfo.setSubjectDifficult( subjectInfoBO.getSubjectDifficult() );
        subjectInfo.setSettleName( subjectInfoBO.getSettleName() );
        subjectInfo.setSubjectType( subjectInfoBO.getSubjectType() );
        subjectInfo.setSubjectScore( subjectInfoBO.getSubjectScore() );
        subjectInfo.setSubjectParse( subjectInfoBO.getSubjectParse() );

        return subjectInfo;
    }

    @Override
    public List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList) {
        if ( subjectInfoList == null ) {
            return null;
        }

        List<SubjectInfoBO> list = new ArrayList<SubjectInfoBO>( subjectInfoList.size() );
        for ( SubjectInfo subjectInfo : subjectInfoList ) {
            list.add( subjectInfoToSubjectInfoBO( subjectInfo ) );
        }

        return list;
    }

    @Override
    public SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo) {
        if ( subjectOptionBO == null && subjectInfo == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        if ( subjectOptionBO != null ) {
            subjectInfoBO.setSubjectAnswer( subjectOptionBO.getSubjectAnswer() );
            List<SubjectAnswerBO> list = subjectOptionBO.getOptionList();
            if ( list != null ) {
                subjectInfoBO.setOptionList( new ArrayList<SubjectAnswerBO>( list ) );
            }
        }
        if ( subjectInfo != null ) {
            subjectInfoBO.setId( subjectInfo.getId() );
            subjectInfoBO.setSubjectName( subjectInfo.getSubjectName() );
            subjectInfoBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
            subjectInfoBO.setSettleName( subjectInfo.getSettleName() );
            subjectInfoBO.setSubjectType( subjectInfo.getSubjectType() );
            subjectInfoBO.setSubjectScore( subjectInfo.getSubjectScore() );
            subjectInfoBO.setSubjectParse( subjectInfo.getSubjectParse() );
        }

        return subjectInfoBO;
    }

    @Override
    public SubjectInfoBO convertInfoToBO(SubjectInfoBO infoBO) {
        if ( infoBO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setPageNo( infoBO.getPageNo() );
        subjectInfoBO.setPageSize( infoBO.getPageSize() );
        subjectInfoBO.setId( infoBO.getId() );
        subjectInfoBO.setSubjectName( infoBO.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( infoBO.getSubjectDifficult() );
        subjectInfoBO.setSettleName( infoBO.getSettleName() );
        subjectInfoBO.setSubjectType( infoBO.getSubjectType() );
        subjectInfoBO.setSubjectScore( infoBO.getSubjectScore() );
        subjectInfoBO.setSubjectParse( infoBO.getSubjectParse() );
        subjectInfoBO.setSubjectAnswer( infoBO.getSubjectAnswer() );
        List<Integer> list = infoBO.getCategoryIds();
        if ( list != null ) {
            subjectInfoBO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = infoBO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoBO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        List<String> list2 = infoBO.getLabelName();
        if ( list2 != null ) {
            subjectInfoBO.setLabelName( new ArrayList<String>( list2 ) );
        }
        List<SubjectAnswerBO> list3 = infoBO.getOptionList();
        if ( list3 != null ) {
            subjectInfoBO.setOptionList( new ArrayList<SubjectAnswerBO>( list3 ) );
        }
        subjectInfoBO.setCategoryId( infoBO.getCategoryId() );
        subjectInfoBO.setLabelId( infoBO.getLabelId() );
        subjectInfoBO.setKeyWord( infoBO.getKeyWord() );
        subjectInfoBO.setCreateUser( infoBO.getCreateUser() );
        subjectInfoBO.setCreateUserAvatar( infoBO.getCreateUserAvatar() );
        subjectInfoBO.setSubjectCount( infoBO.getSubjectCount() );
        subjectInfoBO.setLiked( infoBO.getLiked() );
        subjectInfoBO.setLikedCount( infoBO.getLikedCount() );
        subjectInfoBO.setNextSubjectId( infoBO.getNextSubjectId() );
        subjectInfoBO.setLastSubjectId( infoBO.getLastSubjectId() );

        return subjectInfoBO;
    }

    protected SubjectInfoBO subjectInfoToSubjectInfoBO(SubjectInfo subjectInfo) {
        if ( subjectInfo == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setId( subjectInfo.getId() );
        subjectInfoBO.setSubjectName( subjectInfo.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
        subjectInfoBO.setSettleName( subjectInfo.getSettleName() );
        subjectInfoBO.setSubjectType( subjectInfo.getSubjectType() );
        subjectInfoBO.setSubjectScore( subjectInfo.getSubjectScore() );
        subjectInfoBO.setSubjectParse( subjectInfo.getSubjectParse() );

        return subjectInfoBO;
    }
}
