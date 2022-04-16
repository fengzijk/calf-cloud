package com.calf.cloud.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.calf.cloud.rocketmq.mapper")
public class ProviderRocketMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderRocketMqApplication.class, args);
    }
}
