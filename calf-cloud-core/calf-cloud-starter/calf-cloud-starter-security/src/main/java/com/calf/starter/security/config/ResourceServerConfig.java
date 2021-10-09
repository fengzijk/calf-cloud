/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时06分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:06:18    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.starter.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * -------------------------------------------------
 * <pre>资源配置</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 15:13
 * --------------------------------------------------
 */
@Order(5)
@EnableResourceServer
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final IgnoreUrlPropertiesConfig ignoreUrlPropsConfig;

    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置token存储到redis中
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config = http.requestMatchers().anyRequest().and()
          .authorizeRequests();
        ignoreUrlPropsConfig.getUrls().forEach(url -> config.antMatchers(url).permitAll());
        ignoreUrlPropsConfig.getIgnoreSecurity().forEach(url -> config.antMatchers(url).permitAll());
        config.anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

    }
}