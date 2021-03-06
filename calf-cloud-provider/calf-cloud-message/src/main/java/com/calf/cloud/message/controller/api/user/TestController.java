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

package com.calf.cloud.message.controller.api.user;

import com.calf.cloud.provider.user.api.UserFeignApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <pre>Oauth</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/23 12:51
 */
@RestController
@RequestMapping("/test")
@CrossOrigin
@Tag(name = "test", description = "测试相关")
@Slf4j
public class TestController {


    @Autowired
    private UserFeignApi userFeignApi;

    @Operation(summary = "测试接口", description = "用户测试接口", method = "POST")
    @GetMapping(value = "/getTest")
    @ResponseBody
    public Map<String, Object> test() {
        Map<String, Object> xx = new HashMap<>();
        String apiTest = userFeignApi.getTest();
        log.info("xxxxxxxxxxxxxxx=============={}", apiTest);
        xx.put("111", apiTest);
        return xx;
    }
}
