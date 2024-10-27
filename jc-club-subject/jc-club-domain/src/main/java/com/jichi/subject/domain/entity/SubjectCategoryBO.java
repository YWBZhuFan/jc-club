package com.jichi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-10-03 14:37:23
 */
@Data
public class SubjectCategoryBO implements Serializable {
    private static final long serialVersionUID = -24612063189472278L;
/**
     * 主键
     */
    private Long id;
/**
     * 分类名称
     */
    private String categoryName;
/**
     * 分类类型
     */
    private Integer categoryType;
/**
     * 图标连接
     */
    private String imageUrl;
/**
     * 父级id
     */
    private Long parentId;

    /**
     * 数量
     */
    private Integer count;

    private List<SubjectLabelBO> labelBOList;

}

