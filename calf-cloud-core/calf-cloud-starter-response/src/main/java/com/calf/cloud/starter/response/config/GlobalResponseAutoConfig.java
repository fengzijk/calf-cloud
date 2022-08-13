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
 *  2022-06-19 13:33:40    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response.config;

import com.calf.cloud.starter.response.GlobalResponseHandler;
import com.calf.cloud.starter.response.properties.GlobalResponseProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>统一返回值以及异常处理</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/4 0:38
 */
@Configuration
@EnableConfigurationProperties(GlobalResponseProperties.class)
@ConditionalOnProperty(value = GlobalResponseProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class GlobalResponseAutoConfig {

    @Bean
    public GlobalResponseHandler commonResponseDataAdvice() {
        return new GlobalResponseHandler();
    }
}
