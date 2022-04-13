/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年04月13日 19时16分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2022-04-13 19:16:09    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.message.config;

import com.calf.cloud.starter.response.ResponseResult;
import com.calf.cloud.starter.response.exception.BusinessException;
import com.calf.cloud.starter.response.json.JsonUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OpenFeignErrorDecoder implements ErrorDecoder {
    /**
     * Feign异常解析
     * @param methodKey 方法名
     * @param response 响应体
     * @return BizException
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        try {


            String body = Util.toString(response.body().asReader(Charset.defaultCharset()));

            ResponseResult<?> resultData = JsonUtil.json2Bean(body,ResponseResult.class);
            if(Objects.nonNull(resultData)&&!resultData.isSuccess()){
                return new BusinessException(resultData.getCode(),resultData.getMsg());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}