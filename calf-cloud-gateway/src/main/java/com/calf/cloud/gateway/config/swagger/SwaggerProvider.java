/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 16时24分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 16:24:46    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.gateway.config.swagger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;


/**
 * -------------------------------------------------
 * <pre>实现 Swagger的配置</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 16:27
 * --------------------------------------------------
 */
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {


    public static final String API_URI = "/v3/api-docs";
    public static final String PATH = "Path";

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public SwaggerProvider(GatewayProperties gatewayProperties, RouteLocator routeLocator) {
        this.gatewayProperties = gatewayProperties;
        this.routeLocator = routeLocator;
    }


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
          .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
            .filter(predicateDefinition -> (PATH).equalsIgnoreCase(predicateDefinition.getName()))
            .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
              predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                .replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("3.0");
        return swaggerResource;
    }

}
