/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 00时31分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 00:31:59    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */
package com.calf.cloud.gateway;

import java.util.ArrayList;
import java.util.List;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;


/**
 * -------------------------------------------------
 * <pre>网关启动类</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 0:17
 * --------------------------------------------------
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.calf.cloud", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
  DataSourceAutoConfiguration.class}))
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        for (RouteDefinition definition : definitions) {
            System.out.println("id: " + definition.getId()+ "  "+definition.getUri().toString());
        }
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-service", "");
            swaggerUiConfigParameters.addGroup(name);
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
        });
        return groups;
    }
}