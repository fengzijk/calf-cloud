
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 23时35分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 23:35:56    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.handle.auth.sms;

import com.calf.cloud.uaa.service.UaaUserDetailsService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
*-------------------------------------------------
* <pre>短信验证码验证提供者</pre>
* @author : guozhifeng
* @date : 2021/10/5 23:34
*--------------------------------------------------
*/
@AllArgsConstructor
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private final UaaUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		/**
		 * 调用 {@link UaaUserDetailsService}
		 */
		UserDetails user = userDetailsService.loadUserByMobile((String) authenticationToken.getPrincipal());

		if (Objects.isNull(user)) {
			throw new InternalAuthenticationServiceException("手机号或验证码错误");
		}

		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
