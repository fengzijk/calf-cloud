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
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.provider.user.api;

import com.calf.cloud.starter.response.exception.BusinessException;
import org.springframework.cloud.openfeign.FallbackFactory;

public class UserFeignApiHystrix implements FallbackFactory<UserFeignApi> {

    @Override
    public UserFeignApi create(Throwable cause) {
        return new UserFeignApi() {
            @Override
            public String getTest() {

                throw new BusinessException(1112, "User服务不可用");
            }
        };
    }
}
