/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 14时35分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 14:35:53    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>权限配置文件</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/5 12:35
 * --------------------------------------------------
 */
@Getter
@Setter
@ConfigurationProperties(AuthProperties.PREFIX)
public class AuthProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "calf.cloud.auth";

    /**
     * 是否开启token验证
     */
    private Boolean enabled = Boolean.TRUE;

}