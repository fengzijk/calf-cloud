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
package com.calf.cloud.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;


/**
 * -------------------------------------------------
 * <pre>用户服务启动类</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 0:16
 * --------------------------------------------------
 */
@EnableOpenApi
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMessageApplication.class, args);
    }

}
