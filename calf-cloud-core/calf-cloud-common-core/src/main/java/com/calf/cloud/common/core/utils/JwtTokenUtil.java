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

package com.calf.cloud.common.core.utils;

import com.calf.cloud.common.core.constant.BaseConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Base64;

/**
 * <pre>jwt Token工具</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/7 11:38
 */
public class JwtTokenUtil {

    public static String BEARER = "bearer";
    public static Integer AUTH_LENGTH = 7;


    /**
     * 获取token串
     *
     * @param token token
     * @return java.lang.String
     * @author : guozhifeng
     * @date : 2021/10/7 11:39
     */
    public static String getToken(String token) {
        if ((token != null) && (token.length() > AUTH_LENGTH)) {
            String headStr = token.substring(0, 6).toLowerCase();
            if (headStr.compareTo(BEARER) == 0) {
                token = token.substring(7);
            }
            return token;
        }
        return null;
    }


    /**
     * 解析jwt中的claims信息
     *
     * @param token token
     * @return io.jsonwebtoken.Claims
     * @author : guozhifeng
     * @date : 2021/10/7 11:40
     */
    public static Claims getClaims(String token) {
        String key = Base64.getEncoder().encodeToString(BaseConstant.SIGN_KEY.getBytes());
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
