/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 17时02分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 17:02:55    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.service.impl;

import com.calf.cloud.uaa.pojo.vo.UserInfoVO;
import com.calf.cloud.uaa.service.UaaUserDetailsService;
import com.calf.cloud.uaa.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UaaUserDetailsService {


    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        UserInfoVO userInfo = userService.getUserByUserName(userName);
//        if (Objects.isNull(userInfo)) {
//            throw new BusinessException("该用户：" + userName + "不存在");
//        }
//        userInfo.set(Oauth2Constant.LOGIN_USERNAME_TYPE);
//        userInfo.setUserName(userName);
//        return getUserDetails(userInfo);
        return null;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        return null;
    }


    private UserDetails getUserDetails(UserInfoVO userInfoVO) {
        return null;
    }

}