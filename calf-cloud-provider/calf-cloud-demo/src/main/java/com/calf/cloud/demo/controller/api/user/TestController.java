/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 21时03分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 21:03:44    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.controller.api.user;

import com.calf.cloud.starter.response.exception.BizException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <pre>test</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/23 12:51
 */
@RestController
@RequestMapping("/test")
@CrossOrigin
@Tag(name = "test", description = "测试相关")
public class TestController {


    @Operation(summary = "测试接口", description = "用户测试接口", method = "POST")
    @GetMapping(value = "/getTest")
    @ResponseBody
    public String test() {
        async();
        throw new BizException(111, "11");
    }


    @Async(value = "taskExecutor")
    public void async() {
        System.out.println(Thread.currentThread().getName() + "gggggggggggggggggggggggggggggg");
    }
}
