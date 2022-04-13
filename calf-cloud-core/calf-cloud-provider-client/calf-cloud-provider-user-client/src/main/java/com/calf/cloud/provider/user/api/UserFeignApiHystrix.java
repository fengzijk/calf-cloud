/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年04月10日 08时27分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2022-04-10 08:27:06    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.provider.user.api;

import com.calf.cloud.starter.response.ResponseResult;
import com.calf.cloud.starter.response.exception.BusinessException;
import org.springframework.cloud.openfeign.FallbackFactory;

public class UserFeignApiHystrix implements FallbackFactory<UserFeignApi> {

    @Override
    public UserFeignApi create(Throwable cause) {
        return new UserFeignApi() {
            @Override
            public ResponseResult<String> getTest() {

                throw new BusinessException(1112,"User服务不可用");
            }
        };
    }
}
