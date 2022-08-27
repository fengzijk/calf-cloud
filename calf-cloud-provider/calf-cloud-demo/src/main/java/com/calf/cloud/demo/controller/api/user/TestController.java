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

import com.calf.cloud.demo.handle.TestHandleChain;
import com.calf.cloud.demo.strategy.TestStrategyContextFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @Autowired
    private TestHandleChain testHandleChain;

    @Autowired
    private TestStrategyContextFactory testStrategyContextFactory;

    @GetMapping(value = "/test-handle/{str}")
    public String testHandle(@PathVariable(value = "str") String str) {
        boolean b = testHandleChain.execute(str);
        if (b) {
            return "1111";
        }
        return str;
    }


    @GetMapping(value = "/test-strategy/{str}")
    public String testStrategy(@PathVariable(value = "str") String str) {
        testStrategyContextFactory.get(str).search(str);
        return str;
    }


    @Async(value = "taskExecutor")
    public void async() {
        System.out.println(Thread.currentThread().getName() + "gggggggggggggggggggggggggggggg");
    }
}
