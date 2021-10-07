/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月07日 00时54分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-07 00:54:00    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.starter.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Base64;

public class JwtTokenUtil {

	public static String BEARER = "bearer";
	public static Integer AUTH_LENGTH = 7;

	/**
	 * 获取token串
	 *
	 * @param auth token
	 * @return String
	 */
	public static String getToken(String auth) {
		if ((auth != null) && (auth.length() > AUTH_LENGTH)) {
			String headStr = auth.substring(0, 6).toLowerCase();
			if (headStr.compareTo(BEARER) == 0) {
				auth = auth.substring(7);
			}
			return auth;
		}
		return null;
	}

	/**
	 * 获取jwt中的claims信息
	 *
	 * @param token
	 * @return claim
	 */
	public static Claims getClaims(String token) {
		String key = Base64.getEncoder().encodeToString("USER".getBytes());
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claims;
	}
}
