/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 16时50分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 16:50:28    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    /**
     * 请求成功
     */
    OK(200, "请求成功"),

    /**
     * 请求失败
     */
    BAD_REQUEST(400, "请求失败"),

    /**
     * 请求失败
     */
    NO_HANDLER(404, "资源不存在"),
    /**
     * 系统内部错误
     */
    SYSTEM_ERROR(500, "系统内部错误"),

    /**
     * 用户未登录，请登陆后进行访问
     */
    USER_NEED_LOGIN(11001, "用户未登录，请登陆后进行访问"),
    /**
     * 该用户已在其它地方登录
     */
    USER_MAX_LOGIN(11002, "该用户已在其它地方登录"),
    /**
     * 长时间未操作，自动退出
     */
    USER_LOGIN_TIMEOUT(11003, "长时间未操作，自动退出"),
    /**
     * 用户被禁11005用
     */
    USER_DISABLED(11004, "用户被禁11005用"),
    /**
     * 用户被锁定
     */
    USER_LOCKED(11005, "用户被锁定"),
    /**
     * 用户名或密码错误
     */
    USER_PASSWORD_ERROR(11006, "用户名或密码错误"),
    /**
     * 用户密码过期
     */
    USER_PASSWORD_EXPIRED(11007, "用户密码过期"),
    /**
     * 用户账号过期
     */
    USER_ACCOUNT_EXPIRED(11008, "用户账号过期"),
    /**
     * 没有该用户
     */
    USER_NOT_EXIST(11009, "没有该用户"),
    /**
     * 用户登录失败
     */
    USER_LOGIN_FAIL(11010, "用户登录失败");


    private Integer code;
    private String msg;

    ResponseStatusEnum(Integer code, String name) {
        this.code = code;
        this.msg = name;
    }


    public static String getMsgByCode(Integer code) {
        if (code != null) {
            for (ResponseStatusEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e.getMsg();
                }
            }
        }
        return null;
    }

    public static ResponseStatusEnum getByCode(Integer code) {
        if (code != null) {
            for (ResponseStatusEnum e : values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}