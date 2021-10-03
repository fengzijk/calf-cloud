/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月04日 00时25分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-04 00:25:26    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.starter.autoconfig.response;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -------------------------------------------------
 * <pre>全局返回值与异常处理</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/4 0:47
 * --------------------------------------------------
 */
@ConfigurationProperties(GlobalResponseProperties.PREFIX)
public class GlobalResponseProperties {

    /**
     * 定义过滤拦截头部
     */
    public static final String PREFIX = "global-response";

    public Boolean getEnabled() {
        return enabled;
    }

    private Boolean enabled = Boolean.TRUE;

    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();

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

}

