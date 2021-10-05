
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 23时36分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 23:36:10    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.handle.auth.sms;

import com.calf.cloud.uaa.service.UaaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
*-------------------------------------------------
* <pre>短信验证码登录方式配置</pre>
* @author : guozhifeng
* @date : 2021/10/5 23:35
*--------------------------------------------------
*/
@Component
public class SmsCodeAuthenticationSecurityConfig
		extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private UaaUserDetailsService uaaUserDetailsService;

	@Override
	public void configure(HttpSecurity http) {

		// 过滤器
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

		// 获取验证码提供者
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider(uaaUserDetailsService);

		// 将短信验证码校验器注册到 HttpSecurity， 并将短信验证码过滤器添加在 UsernamePasswordAuthenticationFilter 之前
		http.authenticationProvider(smsCodeAuthenticationProvider)
				.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
