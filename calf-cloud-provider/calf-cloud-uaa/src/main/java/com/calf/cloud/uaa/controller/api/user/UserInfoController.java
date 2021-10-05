
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 23时19分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 23:19:00    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.controller.api.user;


import com.calf.cloud.uaa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * -------------------------------------------------
 * <pre>用户信息接口</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:08
 * --------------------------------------------------
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "UserInfoController", tags = {"用户信息接口"})
public class UserInfoController {

    @Autowired
    private UserService userService;


    @GetMapping("listUserInfo")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String listUserInfo() {
        return "sucess";
    }
}
