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
public enum GrantTypeEnum {

    /**
     * 刷新模式
     */
    REFRESH_TOKEN(1, "refresh_token"),
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE(2, "authorization_code"),
    /**
     * 客户端模式
     */
    CLIENT_CREDENTIALS(3, "client_credentials"),
    /**
     * 密码模式
     */
    PASSWORD(4, "password"),
    /**
     * 简化模式
     */
    IMPLICIT(5, "implicit");


    private Integer code;
    private String name;

    GrantTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getMsgByCode(Integer code) {
        if (code != null) {
            for (GrantTypeEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e.getName();
                }
            }
        }
        return null;
    }

    public static GrantTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (GrantTypeEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
