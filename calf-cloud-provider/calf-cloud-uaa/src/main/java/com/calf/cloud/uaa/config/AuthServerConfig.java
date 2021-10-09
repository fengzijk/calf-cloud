/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时43分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:43:47    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.config;

import com.calf.cloud.common.core.constant.BaseConstant;
import com.calf.cloud.starter.redis.adpter.RedisAdapter;
import com.calf.cloud.uaa.pojo.vo.UserDetailsVo;
import com.calf.cloud.uaa.service.impl.ClientDetailsServiceImpl;
import com.calf.cloud.uaa.service.impl.SingleLoginTokenServices;
import com.calf.cloud.uaa.token.granter.CaptchaTokenGranter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Order(4)
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ClientDetailsServiceImpl clientService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private RedisAdapter redisAdapter;


    @Value("${calf-cloud.uaa.singleLoginFlag:false}")
    private boolean singleLoginFlag = false;


    /**
     * 配置token存储到redis中
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        DefaultTokenServices tokenServices = createDefaultTokenServices();
        // token增强链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // 把jwt增强，与额外信息增强加入到增强链
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        // 配置tokenServices参数
        addUserDetailsService(tokenServices);
        endpoints
          .tokenGranter(tokenGranter(endpoints, tokenServices))
          .tokenServices(tokenServices)
          .accessTokenConverter(jwtAccessTokenConverter());

    }


    private void addUserDetailsService(DefaultTokenServices tokenServices) {
        if (userDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
            tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
          // 允许表单认证请求
          .allowFormAuthenticationForClients()
          // spel表达式 访问公钥端点（/auth/token_key）需要认证
          .tokenKeyAccess("isAuthenticated()")
          // spel表达式 访问令牌解析端点（/auth/check_token）需要认证
          .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clientService.setSelectClientDetailsSql(BaseConstant.SELECT_CLIENT_DETAIL_SQL);
        clientService.setFindClientDetailsSql(BaseConstant.FIND_CLIENT_DETAIL_SQL);
        clients.withClientDetails(clientService);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(BaseConstant.SIGN_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * 重点
     * 先获取已经有的五种授权，然后添加我们自己的进去
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     * @return TokenGranter
     */
    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints, DefaultTokenServices tokenServices) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
//		// 短信验证码模式
//		granters.add(new SmsCodeTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
//				endpoints.getOAuth2RequestFactory(), redisService));
        // 验证码模式
        granters.add(new CaptchaTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
          endpoints.getOAuth2RequestFactory(), redisAdapter));
//		// 社交登录模式
//		granters.add(new SocialTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
//				endpoints.getOAuth2RequestFactory(), redisService, factory));
        // 增加密码模式
        granters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
          endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    /**
     * 创建默认的tokenServices
     *
     * @return DefaultTokenServices
     */
    private DefaultTokenServices createDefaultTokenServices() {
        DefaultTokenServices tokenServices = new SingleLoginTokenServices(singleLoginFlag);
        tokenServices.setTokenStore(redisTokenStore());
        // 支持刷新Token
        tokenServices.setSupportRefreshToken(Boolean.TRUE);
        tokenServices.setReuseRefreshToken(Boolean.FALSE);
        tokenServices.setClientDetailsService(clientService);
        addUserDetailsService(tokenServices);
        return tokenServices;
    }

    /**
     * jwt token增强，添加额外信息
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (oAuth2AccessToken, oAuth2Authentication) -> {

            // 添加额外信息的map
            final Map<String, Object> additionMessage = new HashMap<>(2);
            // 对于客户端鉴权模式，直接返回token
            if (oAuth2Authentication.getUserAuthentication() == null) {
                return oAuth2AccessToken;
            }
            // 获取当前登录的用户
            UserDetailsVo user = (UserDetailsVo) oAuth2Authentication.getUserAuthentication().getPrincipal();

            // 如果用户不为空 则把id放入jwt token中
            if (user != null) {
                additionMessage.put(BaseConstant.USER_ID, String.valueOf(user.getId()));
                additionMessage.put(BaseConstant.USER_NAME, user.getUsername());
                additionMessage.put(BaseConstant.USER_AVATAR, user.getAvatar());
            }
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionMessage);
            return oAuth2AccessToken;
        };
    }
}