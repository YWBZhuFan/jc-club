package com.jichi.practice.server.service;



import com.jichi.practice.api.req.*;
import com.jichi.practice.api.vo.RankVO;
import com.jichi.practice.api.vo.ReportVO;
import com.jichi.practice.api.vo.ScoreDetailVO;
import com.jichi.practice.api.vo.SubjectDetailVO;

import java.util.List;

public interface PracticeDetailService {

    /**
     * 练习提交题目
     */
    Boolean submitSubject(SubmitSubjectDetailReq req);

    /**
     * 提交练题情况
     */
    Boolean submit(SubmitPracticeDetailReq req);

    /**
     * 每题得分详情
     */
    List<ScoreDetailVO> getScoreDetail(GetScoreDetailReq req);

    /**
     * 获得答案详情
     */
    SubjectDetailVO getSubjectDetail(GetSubjectDetailReq req);

    /**
     * 答案解析-评估报告
     */
    ReportVO getReport(GetReportReq req);

    /**
     * 练习榜
     */
    List<RankVO> getPracticeRankList();

    /**
     * 放弃练习
     */
    Boolean giveUp(Long practiceId);

}