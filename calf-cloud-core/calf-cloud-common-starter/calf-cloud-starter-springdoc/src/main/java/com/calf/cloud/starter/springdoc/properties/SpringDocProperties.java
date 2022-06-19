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

package com.calf.cloud.starter.springdoc.properties;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * <pre>Springdoc配置类:</pre>
 *
 * @author : guozhifeng
 * @date : 10/4/2022 上午6:19
 */
@Data
@ConfigurationProperties(prefix = SpringDocProperties.PREFIX)
public class SpringDocProperties {

    public static final String PREFIX = "calf-cloud.springdoc";
    private Boolean enabled = true;

    private BaseDocInfo info;
    private ExternalDocumentation external;
    /**
     * 主要适配 oauth2（http basic配置起来更容易,可以参考oauth2 配置）
     */
    private Map<String, SecurityScheme> securitySchemes;
    /**
     * serverUrl 默认 本地地址
     **/
    private List<Server> servers;


    @Data
    public static class BaseDocInfo {
        private String title = null;
        private String description = null;
        private String termsOfService = null;
        private DocContact contact = null;
        private DocLicense license = null;
        private String version = null;
    }

    @Data
    public static class DocContact {
        private String name = null;
        private String url = null;
        private String email = null;
    }

    @Data
    public static class DocLicense {
        private String name = null;
        private String url = null;
    }
}
