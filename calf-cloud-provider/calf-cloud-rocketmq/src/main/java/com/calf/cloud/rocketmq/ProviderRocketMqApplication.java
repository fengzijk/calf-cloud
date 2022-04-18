package com.calf.cloud.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.calf")
@SpringBootApplication
@MapperScan("com.calf.cloud.rocketmq.mapper")
public class ProviderRocketMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderRocketMqApplication.class, args);
    }
}
