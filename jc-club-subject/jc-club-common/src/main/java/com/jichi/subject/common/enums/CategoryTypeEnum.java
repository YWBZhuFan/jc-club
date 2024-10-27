package com.jichi.subject.common.enums;

import lombok.Getter;

/**
 * 分类类型枚举
 */
@Getter
public enum CategoryTypeEnum {
    PRIMARY(1, "岗位大类"),
    SECOND(2, "二级分类");

    public Integer code;
    public String desc;

    CategoryTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static CategoryTypeEnum getByCode(int code) {
        for (CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
