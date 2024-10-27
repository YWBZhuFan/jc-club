package com.jichi.wx.handler;

import java.util.Map;

/**
 * @author ZhuFan
 * @data 2024/10/11/011 19:53
 */
public interface WeChatMsgHandler {

    WeChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> map);
}
