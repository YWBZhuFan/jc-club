package com.jichi.wx.handler;

/**
 * @author ZhuFan
 * @data 2024/10/11/011 19:47
 */
public enum WeChatMsgTypeEnum {
    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT_MSG("text", "接收用户文本消息");

    private String msgType;

    private String desc;

    WeChatMsgTypeEnum(String msgType, String desc){
        this.msgType = msgType;
        this.desc = desc;
    }

    public static WeChatMsgTypeEnum getByMsgType(String msgType) {
        for (WeChatMsgTypeEnum weChatMsgTypeEnum : WeChatMsgTypeEnum.values()) {
            if (weChatMsgTypeEnum.msgType.equals(msgType)){
                return weChatMsgTypeEnum;
            }
        }
        return null;
    }
}
