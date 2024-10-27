package com.jichi.wx.handler;

import cn.hutool.core.util.RandomUtil;
import com.jichi.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhuFan
 * @data 2024/10/11/011 19:55
 */
@Slf4j
@Component
public class ReceiveTextMsgHandler implements WeChatMsgHandler{

    @Resource private RedisUtil redisUtil;

    private static final String KEY_WORD = "验证码";
    private static final String LOGIN_PREFIX = "loginCode";
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> map) {
        log.info("接收用户文本消息");
        String content = map.get("Content");
        if (!KEY_WORD.equals(content)){
            return "";
        }
        String code = RandomUtil.randomNumbers(4);
        String numContent = "您的验证码为：" + code + "！3分钟后失效！";
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String keyRedis = redisUtil.buildKey(LOGIN_PREFIX, code);
        redisUtil.setNx(keyRedis, fromUserName, 3L, TimeUnit.MINUTES);
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + numContent + "]]></Content>\n" +
                "</xml>";
        return replyContent;
    }
}
