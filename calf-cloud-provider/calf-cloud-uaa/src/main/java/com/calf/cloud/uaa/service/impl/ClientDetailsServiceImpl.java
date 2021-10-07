/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时48分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:48:53    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.service.impl;

import com.calf.cloud.common.core.constant.BaseConstant;
import java.util.Objects;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Setter
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

    @Resource
    private DataSource dataSource;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Bean
    @Primary
    public ClientDetailsServiceImpl clientDetailService() {
        ClientDetailsServiceImpl clientDetailsService = new ClientDetailsServiceImpl(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }

    /**
     * 从redis里读取ClientDetails
     *
     * @param clientId 客户端ID
     * @return ClientDetails
     * @throws InvalidClientException 非法客户端异常
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = (ClientDetails) redisTemplate.opsForValue().get(clientKey(clientId));
        if (Objects.isNull(clientDetails)) {
            clientDetails = getCacheClient(clientId);
        }
        clientDetails.getAuthorizedGrantTypes().add(BaseConstant.CLIENT_CREDENTIALS);
        return clientDetails;
    }

    /**
     * 自定义语句查询，并将数据同步至redis
     *
     * @param clientId 客户端ID
     * @return ClientDetails
     */
    private ClientDetails getCacheClient(String clientId) {
        ClientDetails clientDetails = null;

        try {
            clientDetails = super.loadClientByClientId(clientId);
            if (Objects.nonNull(clientDetails)) {
                redisTemplate.opsForValue().set(clientKey(clientId), clientDetails);
                log.debug("Cache clientId:{}, clientDetails:{}", clientId, clientDetails);
            }
        } catch (Exception e) {
            log.error("Exception for clientId:{}, message:{}", clientId, e.getMessage());
        }
        return clientDetails;
    }

    private String clientKey(String clientId) {
        return BaseConstant.CLIENT_TABLE + ":" + clientId;
    }
}