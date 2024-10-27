package com.jichi.subject.infra.basic.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jichi.subject.infra.basic.entity.SubjectLiked;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目点赞表 表数据库访问层
 *
 * @author jichi
 * @since 2024-10-15 18:40:31
 */
@Repository
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {

    void batchInsertOrUpdate(List<SubjectLiked> subjectLikedList);

    int countByCondition(SubjectLiked subjectLiked);

    List<SubjectLiked> queryPage(SubjectLiked subjectLiked, int start, Integer pageSize);
}

