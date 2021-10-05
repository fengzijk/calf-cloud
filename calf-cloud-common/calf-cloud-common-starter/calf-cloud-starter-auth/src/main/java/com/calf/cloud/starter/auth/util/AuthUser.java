/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 14时41分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 14:41:53    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.auth.util;

import com.calf.cloud.common.core.base.dto.BaseUserInfoDTO;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * -------------------------------------------------
 * <pre>Auth用户</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/5 14:52
 * --------------------------------------------------
 */
@UtilityClass
public class AuthUser {


    /**
     * 获取Authentication
     *
     * @return org.springframework.security.core.Authentication
     * @author : guozhifeng
     * @date : 2021/10/5 14:49
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取用户
     *
     * @return com.calf.cloud.common.core.base.dto.BaseUserInfoDTO
     * @author : guozhifeng
     * @date : 2021/10/5 14:51
     */
    public BaseUserInfoDTO getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }


    /**
     * 获取用户
     *
     * @param authentication 用户认证
     * @return com.calf.cloud.common.core.base.dto.BaseUserInfoDTO 登录用户
     * @author : guozhifeng
     * @date : 2021/10/5 14:49
     */
    public BaseUserInfoDTO getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof BaseUserInfoDTO) {
            return (BaseUserInfoDTO) principal;
        }
        return null;
    }


    /**
     * 获取用户名称
     *
     * @return java.lang.String
     * @author : guozhifeng
     * @date : 2021/10/5 14:51
     */
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }
}