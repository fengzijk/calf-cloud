/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 16时28分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 16:28:57    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response.util;

import com.calf.cloud.starter.response.json.JsonUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

    /**
     * 设置响应
     *
     * @param response HttpServletResponse
     * @param contentType content-type
     * @param status http状态码
     * @param value 响应内容
     * @throws IOException IOException
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
     * @param contentType content-type
     * @param status http状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
//    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType,
//      HttpStatus status, Object value) {
//        response.setStatusCode(status);
//        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
//        Result<?> result = Result.fail(status.value(), value.toString());
//        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
//        return response.writeWith(Mono.just(dataBuffer));
//    }
}