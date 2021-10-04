package com.calf.cloud.user.controller.api.user;


import com.calf.cloud.user.service.impl.UserService;
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
