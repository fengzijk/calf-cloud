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

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>swagger配置属性</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 16:54
 * --------------------------------------------------
 */

@Data
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "swagger";
    /**
     * 全局通用参数
     */
    private Boolean requestParameter = Boolean.FALSE;


    private Boolean enabled = Boolean.TRUE;

    /**
     * 文档扫描包路径
     */
    private List<String> basePackage=new ArrayList<>();

    /**
     * title 如: 用户模块系统接口详情
     */
    private String title = "平台系统接口详情";

    /**
     * 服务文件介绍
     */
    private String description = "在线文档 @author fengzijk";

    /**
     * 服务条款网址
     */
    private String termsOfServiceUrl = "https://www.baidu.com/";

    /**
     * 版本
     */
    private String version = "V1.0";


    private String author = "Fengzijk";

    private String email = "guozhifengvip@163.com";
}