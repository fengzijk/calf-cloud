/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 12时23分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 12:23:28    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.starter.security.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>全局返回值与异常处理</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/4 0:47
 * --------------------------------------------------
 */
@Getter
@ConfigurationProperties(SecurityProperties.PREFIX)
public class SecurityProperties {

    /**
     * 定义过滤拦截头部
     */
    public static final String PREFIX = "calf-cloud.security";

    public Boolean getEnabled() {
        return enabled;
    }

    private Boolean enabled = Boolean.TRUE;


}

