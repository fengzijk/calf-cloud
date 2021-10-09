/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 11时29分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 11:29:48    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.dto;


/**
 * -------------------------------------------------
 * <pre>用户基础信息封装</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 11:23
 * --------------------------------------------------
 */
public class UserHolder {

    private static final ThreadLocal<BaseUserInfoDTO> USER_HOLDER = new InheritableThreadLocal<>();

    public static void add(BaseUserInfoDTO loginInfo) {
        USER_HOLDER.set(loginInfo);
    }

    public static BaseUserInfoDTO getCurrentUser() {

        return USER_HOLDER.get();
    }

    public static void remove() {
        USER_HOLDER.remove();
    }
}
