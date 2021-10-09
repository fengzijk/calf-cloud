/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月07日 11时24分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-07 11:24:26    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.utils;

import com.calf.cloud.common.core.constant.BaseConstant;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * -------------------------------------------------
 * <pre>iwt 安全工具</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/7 11:37
 * --------------------------------------------------
 */
public class JwtSecurityUtil {


    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getHeaderToken(HttpServletRequest request) {
        return request.getHeader(BaseConstant.HEADER_TOKEN);
    }

    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String headerToken = getHeaderToken(request);
        if (StringUtils.isBlank(headerToken)) {
            throw new RuntimeException("没有携带Token信息！");
        }
        return StringUtils.isNotBlank(headerToken) ? JwtTokenUtil.getToken(headerToken) : "";
    }


    /**
     * 从Token解析获取Claims对象
     *
     * @param token token
     * @return io.jsonwebtoken.Claims
     * @author : guozhifeng
     * @date : 2021/10/7 11:55
     */
    public static Claims getClaims(String token) {
        Claims claims = null;
        if (StringUtils.isNotBlank(token)) {
            try {
                claims = JwtTokenUtil.getClaims(token);
            } catch (Exception e) {
                throw new RuntimeException("Token已过期！");
            }
        }
        return claims;
    }
}