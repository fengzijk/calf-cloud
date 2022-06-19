package com.calf.cloud.roketmq.enums;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum TagEnum {


    /**
     *
     */
    HELLO_WORLD("hello_world", "系统发布证书", "user", "user");


    /**
     * 逻辑标记
     */
    private final String tag;
    /**
     * 描述
     */
    private final String desc;
    /**
     * 源系统
     */
    private final String fromService;
    /**
     * 目标系统
     */
    private final String toService;


    TagEnum(String tag, String desc, String fromService, String toService) {
        this.tag = tag;
        this.desc = desc;
        this.fromService = fromService;
        this.toService = toService;
    }


    public static TagEnum getByTag(String tag) {
        if (StringUtils.isBlank(tag)) {
            return null;
        }

        for (TagEnum e : values()) {
            if (e.getTag().equals(tag)) {
                return e;
            }
        }

        return null;
    }
}
