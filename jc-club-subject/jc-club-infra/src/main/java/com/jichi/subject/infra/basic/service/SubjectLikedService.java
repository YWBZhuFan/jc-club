package com.jichi.subject.infra.basic.service;


import com.jichi.subject.infra.basic.entity.SubjectLiked;

import java.util.List;

/**
 * 题目点赞表 表服务接口
 *
 * @author jichi
 * @since 2024-10-15 18:40:31
 */
public interface SubjectLikedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLiked queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int insert(SubjectLiked subjectLiked);

    /**
     * 修改数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int update(SubjectLiked subjectLiked);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询角色
     */
    SubjectLiked queryByCondition(SubjectLiked subjectLiked);

    void batchInsertOrUpdate(List<SubjectLiked> subjectLikedList);

    int countByCondition(SubjectLiked subjectLiked);

    List<SubjectLiked> queryPage(SubjectLiked subjectLiked, int start, Integer pageSize);
}
