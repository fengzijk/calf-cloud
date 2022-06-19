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

package com.calf.cloud.starter.response.properties;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>全局返回值与异常处理</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/4 0:47
 */
@ConfigurationProperties(GlobalResponseProperties.PREFIX)
public class GlobalResponseProperties {

    /**
     * 定义过滤拦截头部
     */
    public static final String PREFIX = "calf-cloud.global-response";
    private final Boolean enabled = Boolean.TRUE;
    private String feignHeader = "Feign-Client";
    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();
    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();

    public Boolean getEnabled() {
        return enabled;
    }

    public List<String> getAdviceFilterPackage() {
        return adviceFilterPackage;
    }

    public void setAdviceFilterPackage(List<String> adviceFilterPackage) {
        this.adviceFilterPackage = adviceFilterPackage;
    }

    public List<String> getAdviceFilterClass() {
        return adviceFilterClass;
    }

    public void setAdviceFilterClass(List<String> adviceFilterClass) {
        this.adviceFilterClass = adviceFilterClass;
    }

    public String getFeignHeader() {
        return feignHeader;
    }

    public void setFeignHeader(String feignHeader) {
        this.feignHeader = feignHeader;
    }
}

