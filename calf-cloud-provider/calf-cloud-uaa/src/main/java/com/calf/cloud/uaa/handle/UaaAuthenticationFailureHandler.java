/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 16时38分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 16:38:42    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.handle;

import com.calf.cloud.starter.response.ResponseResult;
import com.calf.cloud.starter.response.ResponseStatusEnum;
import com.calf.cloud.starter.response.util.ResponseResultUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Slf4j
public class UaaAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
        ResponseResult<?> result = null;
        String username = request.getParameter("username");
        if (exception instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期", username);
            result = ResponseResult.fail(ResponseStatusEnum.USER_ACCOUNT_EXPIRED);

        } else if (exception instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", username);
            result = ResponseResult.fail(ResponseStatusEnum.USER_PASSWORD_ERROR);

        } else if (exception instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期", username);
            result = ResponseResult.fail(ResponseStatusEnum.USER_PASSWORD_EXPIRED);

        } else if (exception instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用", username);
            result = ResponseResult.fail(ResponseStatusEnum.USER_DISABLED);

        } else if (exception instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定", username);
            result = ResponseResult.fail(ResponseStatusEnum.USER_LOCKED);

        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", username));
            result = ResponseResult.fail(ResponseStatusEnum.USER_LOGIN_FAIL);

        } else {
            // 其他错误
            log.error(String.format("[登录失败] - [%s]其他错误", username), exception);
            result = ResponseResult.fail(ResponseStatusEnum.USER_LOGIN_FAIL);
        }
        ResponseResultUtil.responseWriter(response, MediaType.APPLICATION_JSON_VALUE, HttpStatus.UNAUTHORIZED.value(), result);

    }
}