/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 16时12分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 16:12:22    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.controller.api.user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Api(tags = "Oauth2管理")
public class OauthController {

    private final TokenEndpoint tokenEndpoint;


    @GetMapping("/token")
    @ApiOperation(value = "用户GET方式登录", notes = "用户登录Get")
    public Map<String, Object> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {
        return customJwt(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }


    @PostMapping("/token")
    @ApiOperation(value = "用户POST方式登录", notes = "用户登录Post")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "grant_type", required = true, value = "授权类型", paramType = "query"),
      @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
      @ApiImplicitParam(name = "password", value = "密码", paramType = "query"),
      @ApiImplicitParam(name = "scope", required = true, value = "使用范围", paramType = "query"),
    })
    public Map<String, Object> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {
        Map<String, Object> custom = customJwt(tokenEndpoint.postAccessToken(principal, parameters).getBody());
        return custom;
    }


    /**
     * 自定义返回格式
     *
     * @param accessToken token
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author : guozhifeng
     * @date : 2021/10/7 11:50
     */
    private Map<String, Object> customJwt(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return data;
    }
}