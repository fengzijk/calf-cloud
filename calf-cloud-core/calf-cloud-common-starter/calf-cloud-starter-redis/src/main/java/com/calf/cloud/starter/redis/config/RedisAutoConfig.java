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

package com.calf.cloud.starter.redis.config;

import com.calf.cloud.starter.redis.adpter.RedisAdapter;
import com.calf.cloud.starter.redis.redission.DistributedLocker;
import com.calf.cloud.starter.redis.redission.RedissonDistributedLocker;
import com.calf.cloud.starter.redis.redission.RedissonLockUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 郭志峰
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(value = RedisProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class RedisAutoConfig {


    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        @SuppressWarnings("all")
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 单机模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = RedisProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
    RedissonClient redissonSingle(@Value("${spring.redis.host}") String host,
                                  @Value("${spring.redis.port}") int port,
                                  @Value("${spring.redis.password}") String password,
                                  @Value("${spring.redis.database}") String database
    ) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setTimeout(3000)
                .setConnectionPoolSize(32)
                .setConnectionMinimumIdleSize(8);

        if (StringUtil.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }

        serverConfig.setDatabase(Integer.parseInt(database));

        return Redisson.create(config);

    }

    @Bean(name = "redisAdapter")
    @ConditionalOnBean(name = "redisTemplate")
    public RedisAdapter redisAdapter() {
        return new RedisAdapter();
    }


    /**
     * 装配locker类，并将实例注入到RedissonLockUtil中
     *
     * @return com.calf.cloud.starter.redis.redisson.DistributedLocker
     * @author : fengzijk
     * @date : 2021/10/5 13:07
     */
    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    DistributedLocker distributedLocker(RedissonClient redissonSingle) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonSingle);
        RedissonLockUtil.setLocker(locker);
        return locker;
    }
}
