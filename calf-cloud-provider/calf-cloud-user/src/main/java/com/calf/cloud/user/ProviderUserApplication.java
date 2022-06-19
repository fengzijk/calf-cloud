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
package com.calf.cloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * <pre>用户服务启动类</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 0:16
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.calf.cloud.user.mapper")
public class ProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class, args);
    }
}
