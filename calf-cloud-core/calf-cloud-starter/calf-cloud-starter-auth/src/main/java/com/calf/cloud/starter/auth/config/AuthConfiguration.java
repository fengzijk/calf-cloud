/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 14时34分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 14:34:57    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.auth.config;

import com.calf.cloud.starter.auth.properties.AuthProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * -------------------------------------------------
 * <pre>权限自动配置</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 14:37
 * --------------------------------------------------
 */
@Configuration
@ComponentScan(value = "com.calf.cloud.starter.auth")
@EnableConfigurationProperties(AuthProperties.class)
@ConditionalOnProperty(value = AuthProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class AuthConfiguration {

}