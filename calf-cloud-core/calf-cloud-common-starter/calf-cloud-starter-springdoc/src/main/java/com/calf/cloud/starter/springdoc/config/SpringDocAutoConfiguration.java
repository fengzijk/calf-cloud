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

package com.calf.cloud.starter.springdoc.config;


import com.calf.cloud.starter.springdoc.properties.SpringDocProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;


/**
 * <pre>Springdoc自动配置</pre>
 *
 * @author : guozhifeng
 * @date : 10/4/2022 上午7:37
 */
@Configuration
@ConditionalOnProperty(name = "calf-cloud.springdoc.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SpringDocProperties.class)
public class SpringDocAutoConfiguration {
    @Value("${server.port:8080}")
    private int port;
    @Value("${spring.mvc.servlet.path:/}")
    private String servletPath;


    @Bean
    @ConditionalOnMissingBean
    public SpringDocProperties springDocProperties() {
        return new SpringDocProperties();
    }

    @Bean
    public OpenAPI springDocOpenApi(SpringDocProperties springDocProperties) {
        if (springDocProperties == null) {
            return new OpenAPI();
        }
        //配置认证、请求头参数
        Components components = new Components();
        List<SecurityRequirement> security = new ArrayList<>();
        if (!CollectionUtils.isEmpty(springDocProperties.getSecuritySchemes())) {
            for (Map.Entry<String, SecurityScheme> entry : springDocProperties.getSecuritySchemes().entrySet()) {
                components.addSecuritySchemes(entry.getKey(), entry.getValue());
                SecurityRequirement requirement = new SecurityRequirement();
                requirement.addList(entry.getKey());
                security.add(requirement);
            }
        }

        Info info = new Info();
        SpringDocProperties.BaseDocInfo baseDocInfo = springDocProperties.getInfo();
        if (baseDocInfo != null) {
            License license = new License();
            SpringDocProperties.DocLicense plusLicense = baseDocInfo.getLicense();
            if (plusLicense != null) {
                license.name(plusLicense.getName()).url(plusLicense.getUrl());
            }
            Contact contact = new Contact();
            SpringDocProperties.DocContact plusContact = baseDocInfo.getContact();
            if (plusContact != null) {
                contact.email(plusContact.getEmail()).name(plusContact.getName()).url(plusContact.getUrl());
            }
            info.title(baseDocInfo.getTitle()).description(baseDocInfo.getDescription())
                    .termsOfService(baseDocInfo.getTermsOfService()).version(baseDocInfo.getVersion() == null ? version() : baseDocInfo.getVersion())
                    .contact(contact).license(license)
            ;
        }
        // 接口调试路径
        List<Server> servers = springDocProperties.getServers();
        if (CollectionUtils.isEmpty(servers)) {
            servers = new ArrayList<>();
        }
        servers.add(localServer());
        return new OpenAPI().components(components)
                .servers(servers)
                .info(info).externalDocs(springDocProperties.getExternal())
                .security(security);
    }

    private Server localServer() {
        Server server = new Server();
        server.setUrl("http://localhost:" + port + "" + servletPath);
        server.setDescription("本地服务环境");
        return server;
    }

    private String version() {
        return "Spring Boot Version: " + SpringBootVersion.getVersion();
    }

}
