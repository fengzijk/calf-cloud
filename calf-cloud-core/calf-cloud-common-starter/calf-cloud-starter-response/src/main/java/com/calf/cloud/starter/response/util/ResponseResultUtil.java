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

package com.calf.cloud.starter.response.util;

import com.calf.cloud.starter.response.ResponseResult;
import com.calf.cloud.starter.response.json.JsonUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * <pre>Response响应工具类</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/7 11:53
 */
public class ResponseResultUtil {


    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     * @author : guozhifeng
     * @date : 2021/10/7 11:52
     */
    public static void responseWriter(HttpServletResponse response, String contentType,
                                      int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JsonUtil.tojson(value).getBytes());
    }


    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param value    响应内容
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author : guozhifeng
     * @date : 2021/10/7 11:52
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response,
                                                   HttpStatus status, Object value) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResponseResult<String> result = new ResponseResult<String>().setCode(status.value())
                .setMsg(value.toString());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JsonUtil.tojson(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
