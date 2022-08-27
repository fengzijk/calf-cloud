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
import java.util.List;

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
     * 系统用户信息
     */
    private BaseManagerVO baseManagervo;

    /**
     * 系统权限标识组
     */
    private List<String> permissions;

    /**
     * 系统角色标识组
     */

    private List<String> roleIds;

    /**
     * 登录类型　1：用户名密码登录　2：手机号登录　3：社交登录
     */
    private int loginType;

    /**
     * 用户名
     */
    private String username;

    private Long id;

    private Integer status = 1;
}
