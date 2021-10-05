/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 17时01分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 17:01:40    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UaaUserDetailsService extends UserDetailsService {


    /**
     * 根据手机号登录
     *
     * @param mobile 手机号码
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author : fengzijk
     * @date : 2021/10/5 17:11
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;


}