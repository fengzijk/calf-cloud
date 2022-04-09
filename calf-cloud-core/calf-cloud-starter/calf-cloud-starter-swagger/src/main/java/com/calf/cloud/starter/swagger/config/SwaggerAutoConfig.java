/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 13时14分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 13:14:04    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.swagger.config;

import com.calf.cloud.starter.swagger.properties.SwaggerProperties;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * -------------------------------------------------
 * <pre>swagger 配置</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 16:39
 * --------------------------------------------------
 */
@Configuration
@EnableSwagger2WebMvc
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(value = "calf-cloud.swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerAutoConfig implements WebMvcConfigurer {


    @Resource
    private SwaggerProperties swaggerProperties;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }


    /**
     * Swagger忽略的参数类型
     */
    private final Class[] ignoredParameterTypes = new Class[]{
      ServletRequest.class,
      ServletResponse.class,
      HttpServletRequest.class,
      HttpServletResponse.class,
      HttpSession.class,
      ApiIgnore.class,
      Principal.class,
      Map.class
    };

    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
          //资源
          .globalResponseMessage(RequestMethod.GET, new ArrayList<>())
          .globalResponseMessage(RequestMethod.PUT, new ArrayList<>())
          .globalResponseMessage(RequestMethod.POST, new ArrayList<>())
          .globalResponseMessage(RequestMethod.DELETE, new ArrayList<>())
          //是否启动
          .enable(swaggerProperties.getEnabled())
          //头部信息
          .apiInfo(apiInfo())
          .select()
          .apis(RequestHandlerSelectors.any())
          //过滤某个路径
          .paths(PathSelectors.regex("/error"))
          .build()
          //协议
          .protocols(newHashSet("https", "http"))
          .securitySchemes(securitySchemes()).ignoredParameterTypes(ignoredParameterTypes)
          .securityContexts(securityContexts());

        // 设置全局参数
        if (swaggerProperties.getRequestParameter()) {
            docket.globalOperationParameters(globalRequestParameters());
        }

        return docket;
    }

    private List<Parameter> globalRequestParameters() {

        ParameterBuilder userId = new ParameterBuilder();

        userId.name("userId").description("用户Id").modelRef(new ModelRef("string")).parameterType("header").required(false);

        ParameterBuilder token = new ParameterBuilder();
        token.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false);

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


    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
          SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("^(?!auth).*$"))
            .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
