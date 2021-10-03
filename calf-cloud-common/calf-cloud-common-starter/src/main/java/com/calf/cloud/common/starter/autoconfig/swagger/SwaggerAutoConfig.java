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
 *  2021-10-03 16:49:26    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.starter.autoconfig.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * -------------------------------------------------
 * <pre>swagger 配置</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 16:39
 * --------------------------------------------------
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfig {


    @Resource
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket api() {

        Predicate<RequestHandler> selector = null;
        if (Objects.nonNull(swaggerProperties.getBasePackage()) && swaggerProperties.getBasePackage().size() > 0) {
            swaggerProperties.getBasePackage().forEach(a -> {
                Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage(a);
                selector.or(predicate);
            });
        }
        Docket docket = new Docket(DocumentationType.OAS_30)
          //资源
          .globalResponses(HttpMethod.GET, new ArrayList<>())
          .globalResponses(HttpMethod.PUT, new ArrayList<>())
          .globalResponses(HttpMethod.POST, new ArrayList<>())
          .globalResponses(HttpMethod.DELETE, new ArrayList<>())
          //是否启动
          .enable(swaggerProperties.getEnabled())
          //头部信息
          .apiInfo(apiInfo())
          .select()
          .apis(RequestHandlerSelectors.any())
          //过滤某个路径
          .paths(PathSelectors.regex("/error").negate())
          .build()
          //协议
          .protocols(newHashSet("https", "http"))
          .securitySchemes(securitySchemes())
          .securityContexts(securityContexts());

        // 设置全局参数
        if (swaggerProperties.getRequestParameter()) {
            docket.globalRequestParameters(globalRequestParameters());
        }

        return docket;
    }

    private List<RequestParameter> globalRequestParameters() {

        RequestParameterBuilder userId = new RequestParameterBuilder();
        userId.name("userId")
          .description("登录用户Id")
          .in(ParameterType.HEADER)
          .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)))
          .required(false)
          .build();

        RequestParameterBuilder token = new RequestParameterBuilder();
        token.name("token")
          .description("token")
          .in(ParameterType.HEADER)
          .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)))
          .required(false)
          .build();

        return Arrays.asList(userId.build(), token.build());

    }


    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
          .title(swaggerProperties.getTitle())
          .description(swaggerProperties.getDescription())
          .contact(new Contact(swaggerProperties.getAuthor(), null, swaggerProperties.getEmail()))
          .version(swaggerProperties.getVersion())
          .build();
    }

    /**
     * 设置接口单独的授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("BASE_TOKEN", "token", "header"));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
          SecurityContext.builder()
            .securityReferences(
              Collections.singletonList(new SecurityReference("BASE_TOKEN",
                new AuthorizationScope[]{new AuthorizationScope("global", "")}
              )))
            //.forPaths(PathSelectors.any())
            .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}
