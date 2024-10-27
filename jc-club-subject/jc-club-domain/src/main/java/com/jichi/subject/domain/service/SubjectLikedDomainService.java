package com.jichi.subject.domain.service;


import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.domain.entity.SubjectLikedBO;

/**
 * 题目点赞表 领域service
 *
 * @author jichi
 * @since 2024-10-15 18:40:31
 */
public interface SubjectLikedDomainService {

    /**
     * 添加 题目点赞表 信息
     */
    void add(SubjectLikedBO subjectLikedBO);

    /**
     * 更新 题目点赞表 信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 获取当前是否被点赞过
     */
    Boolean isLiked(String subjectId, String uerId);

    /**
     * 获取点赞数量
     */
    public Integer getLikedCount(String subjectId);

    /**
     * 删除 题目点赞表 信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);

    /**
     * 同步点赞数据
     */
    void syncLiked();

    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);

    void syncLikedByMsg(SubjectLikedBO subjectLikedBO);
}
