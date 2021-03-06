/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年06月19日 13时33分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.enums.user;

import lombok.Getter;

@Getter
public enum LoginTypeEnum {

    /**
     * 请求成功
     */
    USER_NAME(1, "用户名登录"),

    /**
     * 请求失败
     */
    MOBILE(2, "手机号登录");


    private Integer code;
    private String name;

    LoginTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getMsgByCode(Integer code) {
        if (code != null) {
            for (LoginTypeEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e.getName();
                }
            }
        }
        return null;
    }

    public static LoginTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (LoginTypeEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
