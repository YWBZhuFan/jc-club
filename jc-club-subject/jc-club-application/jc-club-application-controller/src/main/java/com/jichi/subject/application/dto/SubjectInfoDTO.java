package com.jichi.subject.application.dto;

import com.jichi.subject.common.entity.PageInfo;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 题目信息dto
 *
 * @author makejava
 * @since 2024-10-05 14:53:00
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
    private static final long serialVersionUID = 191572953241236310L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 题目分类id
     */
    private List<Integer> categoryIds;
    /**
     * 题目标签id
     */
    private List<Integer> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;

    private Integer categoryId;

    private Long labelId;

    private String keyWord;

    /**
     * 是否被当前用户点赞
     */
    private Boolean liked;

    /**
     * 当前题目点赞的数量
     */
    private Integer likedCount;

    /**
     * 下一题
     */
    private Long nextSubjectId;

    /**
     * 上一题
     */
    private Long lastSubjectId;
}

