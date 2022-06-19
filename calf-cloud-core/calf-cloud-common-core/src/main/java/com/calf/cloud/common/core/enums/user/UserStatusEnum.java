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
public enum UserStatusEnum {

    /**
     *
     */
    ENABLE(0, "可用"),

    /**
     * 请求失败
     */
    DISABLE(1, "不可用");


    private Integer code;
    private String name;

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getMsgByCode(Integer code) {
        if (code != null) {
            for (UserStatusEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e.getName();
                }
            }
        }
        return null;
    }

    public static UserStatusEnum getByCode(Integer code) {
        if (code != null) {
            for (UserStatusEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
