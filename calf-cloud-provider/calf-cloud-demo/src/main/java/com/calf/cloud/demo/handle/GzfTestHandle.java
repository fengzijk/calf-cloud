/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 23时23分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 23:23:36    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.handle;

import com.calf.cloud.demo.service.UserService;
import com.calf.cloud.starter.response.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Timothy
 */

@Slf4j
@Component
@Order(2)
public class GzfTestHandle implements TestHandle {


    @Autowired
    private UserService userService;

    @Override
    public Boolean execute(String str) {

        if (StringUtils.contains(str, "gzf")) {
            log.info(JsonUtil.toJson(userService.findByName(str)));
            log.info("========GzfTestHandle========fz" + str);
            return Boolean.TRUE;
        }
        if (StringUtils.contains(str, "gzf1")) {
            log.info("========GzfTestHandle========fz" + str);
            return Boolean.FALSE;
        }
        return null;
    }
}
