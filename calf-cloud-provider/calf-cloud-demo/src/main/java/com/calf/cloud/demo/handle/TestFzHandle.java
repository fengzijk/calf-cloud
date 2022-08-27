/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 23时27分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 23:27:09    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Order(1)
public class TestFzHandle implements TestHandle {
    @Override
    public Boolean execute(String str) {

        if (StringUtils.contains(str, "fz")) {
            log.info("========TestFzHandle========fz" + str);
            return Boolean.TRUE;
        }
        if (StringUtils.contains(str, "fz1")) {
            log.info("========TestFzHandle========fz1" + str);
            return Boolean.FALSE;
        }
        return null;
    }
}
