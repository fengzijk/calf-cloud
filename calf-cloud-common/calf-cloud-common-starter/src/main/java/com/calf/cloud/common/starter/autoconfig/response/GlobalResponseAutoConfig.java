/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月04日 00时26分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-04 00:26:14    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.starter.autoconfig.response;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * -------------------------------------------------
 * <pre>统一返回值以及异常处理</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/4 0:38
 * --------------------------------------------------
 */
@Configuration
@EnableConfigurationProperties(GlobalResponseProperties.class)
public class GlobalResponseAutoConfig {

    @Bean
    public GlobalResponseHandler commonResponseDataAdvice(GlobalResponseProperties globalResponseProperties) {
        return new GlobalResponseHandler(globalResponseProperties);
    }
}
