/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月07日 00时44分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-07 00:44:19    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.gateway.filter;

import com.calf.cloud.common.core.constant.BaseConstant;
import com.calf.cloud.common.core.utils.JwtSecurityUtil;
import com.calf.cloud.common.core.utils.JwtTokenUtil;
import com.calf.cloud.gateway.constant.FilterOrderConstant;
import com.calf.cloud.starter.redis.adpter.RedisAdapter;
import com.calf.cloud.starter.response.util.ResponseResultUtil;
import com.calf.starter.security.config.IgnoreUrlPropertiesConfig;
import io.jsonwebtoken.Claims;
import jodd.util.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * -------------------------------------------------
 * <pre>用户鉴权过滤器</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/7 11:57
 * --------------------------------------------------
 */
@Slf4j
public class PreUaaFilter implements GlobalFilter, Ordered {


    @Autowired
    private RedisAdapter redisAdapter;


    @Autowired
    private IgnoreUrlPropertiesConfig ignoreUrlPropertiesConfig;
    /**
     * 路径前缀以/
     */
    public static final String PATH_PREFIX = "/";

    /**
     * 索引自1开头检索，跳过第一个字符就是检索的字符的问题
     */
    public static final int FROM_INDEX = 1;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //　如果在忽略的url里，则跳过
        String path = replacePrefix(exchange.getRequest().getURI().getPath());
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        if (ignore(path) || ignore(requestUrl)) {
            return chain.filter(exchange);
        }

        // 验证token是否有效
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst(BaseConstant.HEADER_TOKEN);
        if (headerToken == null) {
            return unauthorized(resp, "没有携带Token信息！");
        }
        String token = JwtTokenUtil.getToken(headerToken);
        Claims claims = JwtSecurityUtil.getClaims(token);
        if (claims == null) {
            return unauthorized(resp, "token已过期或验证不正确！");
        }
        boolean hasKey = redisAdapter.hasKey("auth:" + token);
        log.debug("查询token是否存在: " + hasKey);
        if (!hasKey) {
            return unauthorized(resp, "登录超时，请重新登录");
        }
        return chain.filter(exchange);
    }

    /**
     * 检查是否忽略url
     *
     * @param path 路径
     * @return boolean
     */
    private boolean ignore(String path) {
        return ignoreUrlPropertiesConfig.getUrls().stream()
          .map(url -> url.replace("/**", ""))
          .anyMatch(path::startsWith);
    }

    /**
     * 移除模块前缀
     *
     * @param path 路径
     * @return String
     */
    private String replacePrefix(String path) {
        if (path.startsWith(PATH_PREFIX)) {
            return path.substring(path.indexOf(StringPool.SLASH, FROM_INDEX));
        }
        return path;
    }

    private Mono<Void> unauthorized(ServerHttpResponse resp, String msg) {
        return ResponseResultUtil.webFluxResponseWriter(resp, HttpStatus.UNAUTHORIZED, msg);
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.UAA_FILTER_ORDER;
    }

}