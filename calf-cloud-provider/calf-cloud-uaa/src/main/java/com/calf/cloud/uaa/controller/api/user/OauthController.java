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

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * -------------------------------------------------
 * <pre>Oauth</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/23 12:51
 * --------------------------------------------------
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private TokenEndpoint tokenEndpoint;


    /**
     * get 方式获取Token
     */
    @GetMapping("/token")
    public Map<String, Object> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {
        return customJwt(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }


    /**
     * post 方式获取token
     */
    @PostMapping("/token")
    public Map<String, Object> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {
        return customJwt(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }


    /**
     * 自定义返Token回格式
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