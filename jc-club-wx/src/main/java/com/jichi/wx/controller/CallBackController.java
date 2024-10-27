package com.jichi.wx.controller;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.jichi.wx.handler.WeChatMsgFactory;
import com.jichi.wx.handler.WeChatMsgHandler;
import com.jichi.wx.utils.MessageUtil;
import com.jichi.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZhuFan
 * @data 2024/10/11/011 17:28
 */
@RestController
@Slf4j
public class CallBackController {

    @Resource private WeChatMsgFactory wechatMsgFactory;

    private static final String token = "ywb123456";

    @RequestMapping("/test")
    public String callBack(String msg) {
        log.info("callBack msg:{}", msg);
        return "success";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "未知错误!";
    }

    @PostMapping("/callback")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {

        log.info("接收到微信的请求：requestBody:{}, signature:{}, timestamp:{}, nonce:{}",
                requestBody, signature, timestamp, nonce);
        Map<String, String> map = MessageUtil.parseXml(requestBody);
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String event = map.get("Event") == null ? "" : map.get("Event");
        log.info("接收到微信的请求：fromUserName:{}, toUserName:{}, msgType:{}, event:{}",
                fromUserName, toUserName, msgType, event);
        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if (!StringUtils.isEmpty(event)){
            sb.append(".").append(event);
        }
        WeChatMsgHandler weChatMsgHandler = wechatMsgFactory.getHandler(sb.toString());
        if ((Objects.isNull(weChatMsgHandler))){
            return "unknown";
        }
        String replyContent = weChatMsgHandler.dealMsg(map);
        return replyContent;
    }
}
