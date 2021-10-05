/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 12时44分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 12:44:27    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.redis.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>redis配置</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 13:12
 * --------------------------------------------------
 */
@Getter
@Setter
@ConfigurationProperties(RedisProperties.PREFIX)
public class RedisProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "calf-cloud.redis";
    /**
     * 是否开启
     */
    private Boolean enabled = true;

    private Boolean redissonLockEnable = true;
}