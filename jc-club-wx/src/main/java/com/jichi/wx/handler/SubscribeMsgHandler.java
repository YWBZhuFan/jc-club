package com.jichi.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ZhuFan
 * @data 2024/10/11/011 19:54
 */
@Slf4j
@Component
public class SubscribeMsgHandler implements WeChatMsgHandler{
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(Map<String, String> map) {
        log.info("用户关注事件");
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String subscribeContent = "无人扶我青云志，我自踏雪至山巅！giggity";
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + subscribeContent + "]]></Content>\n" +
                "</xml>";;
        return content;
    }
}
