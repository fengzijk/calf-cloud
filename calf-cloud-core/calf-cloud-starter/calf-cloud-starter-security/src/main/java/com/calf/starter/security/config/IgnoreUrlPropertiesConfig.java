/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时07分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:07:20    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.starter.security.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * -------------------------------------------------
 * <pre>功能描述:</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 15:08
 * --------------------------------------------------
 */
@ConfigurationProperties(prefix = "calf-cloud.security.ignore")
@Component
@Data
public class IgnoreUrlPropertiesConfig {

    /**
     * 认证中心默认忽略验证地址
     */
    private static final String[] SECURITY_ENDPOINTS = {
      "/auth/**",
      "/oauth/token",
      "/login/*",
      "/actuator/**",
      "/v2/api-docs",
      "/doc.html",
      "/webjars/**",
      "**/favicon.ico",
      "/swagger-resources/**",
      "/swagger**/**",
      "/v3/**",
      "/doc.html"

    };

    private List<String> urls = new ArrayList<>();

    private List<String> client = new ArrayList<>();

    private List<String> ignoreSecurity = new ArrayList<>();

    /**
     * 首次加载合并ENDPOINTS
     */
    @PostConstruct
    public void initIgnoreSecurity() {
        Collections.addAll(ignoreSecurity, SECURITY_ENDPOINTS);
    }

}