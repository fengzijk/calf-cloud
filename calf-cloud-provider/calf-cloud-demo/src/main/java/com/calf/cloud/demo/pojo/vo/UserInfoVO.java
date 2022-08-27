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

package com.calf.cloud.demo.pojo.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * <pre>用户信息封装</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 17:36
 */

@Data
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -7657663783681647907L;
    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1:男 2:女)
     */
    private Boolean sex;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

}
