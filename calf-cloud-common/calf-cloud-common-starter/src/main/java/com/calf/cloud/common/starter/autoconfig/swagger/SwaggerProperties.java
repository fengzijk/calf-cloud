/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 16时49分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 16:49:31    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.starter.autoconfig.swagger;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>swagger配置属性</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 16:54
 * --------------------------------------------------
 */

@Data
@ConfigurationProperties(prefix = "springfox.documentation.enable")
@ConditionalOnProperty(prefix = "springfox.documentation", value = "true", havingValue = "true")
public class SwaggerProperties {

    /**
     * 全局通用参数
     */
    private String globalParameters;
}