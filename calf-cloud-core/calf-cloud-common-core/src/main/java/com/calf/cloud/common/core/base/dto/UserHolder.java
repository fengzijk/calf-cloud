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

package com.calf.cloud.common.core.base.dto;


/**
 * <pre>用户基础信息封装</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 11:23
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
