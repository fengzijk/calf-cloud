
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


import com.calf.cloud.starter.auth.annotation.PermissionAuth;
import com.calf.cloud.uaa.pojo.entity.UserInfoEntity;
import com.calf.cloud.uaa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PermissionAuth
    @GetMapping("listUserInfo")
    public String listUserInfo() {
        return "sucess";
    }


    /**
     * 设置用户密码
     *
     * @param user 用户信息
     * @return Result
     */
    @PostMapping("/set-password")
    @ApiOperation(value = "用户密码设置", notes = "用户密码设置")
    public Boolean setPassword(@RequestBody UserInfoEntity user) {
        String pwd = null;
        if (StringUtils.isNotBlank(user.getPassword())) {
            pwd = passwordEncoder.encode(user.getPassword());
            log.info(pwd);
        }
        user.setPassword(pwd);
        if (user.getId() == null) {
           // throw new BusinessException("请求ID不能为空");
        }
        return true;
    }
}
