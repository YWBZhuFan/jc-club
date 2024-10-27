package com.jichi.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 */
@Getter
public enum IsDeletedFlagEnum {
    DELETED(1, "已删除"),
    UN_DELETED(0, "未删除");

    public Integer code;
    public String desc;

    IsDeletedFlagEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static IsDeletedFlagEnum getByCode(int code) {
        for (IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
