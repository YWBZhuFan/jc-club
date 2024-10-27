package com.jingdianjichi.interview.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingdianjichi.interview.api.common.PageResult;
import com.jingdianjichi.interview.api.req.InterviewHistoryReq;
import com.jingdianjichi.interview.api.req.InterviewSubmitReq;
import com.jingdianjichi.interview.api.vo.InterviewHistoryVO;
import com.jingdianjichi.interview.api.vo.InterviewResultVO;
import com.jingdianjichi.interview.server.entity.po.InterviewHistory;

/**
 * 面试汇总记录表(InterviewHistory)表服务接口
 *
 * @author makejava
 * @since 2024-05-23 22:56:03
 */
public interface InterviewHistoryService extends IService<InterviewHistory> {

    void logInterview(InterviewSubmitReq req, InterviewResultVO submit);


    PageResult<InterviewHistoryVO> getHistory(InterviewHistoryReq req);

}
