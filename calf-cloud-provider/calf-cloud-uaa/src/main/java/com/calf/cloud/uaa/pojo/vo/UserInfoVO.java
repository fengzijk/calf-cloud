/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 17时35分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 17:35:54    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.pojo.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * -------------------------------------------------
 * <pre>用户信息封装</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 17:36
 * --------------------------------------------------
 */

@Data
@ApiModel(value = "用户信息封装")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -7657663783681647907L;

    /**
     * 系统用户信息
     */
    @ApiModelProperty("系统用户信息")
    private BaseManagerVO baseManagervo;

    /**
     * 系统权限标识组
     */
    @ApiModelProperty("系统权限标识组")
    private List<String> permissions;

    /**
     * 系统角色标识组
     */
    @ApiModelProperty(value = "系统角色标识组")
    private List<String> roleIds;

    /**
     * 登录类型　1：用户名密码登录　2：手机号登录　3：社交登录
     */
    @ApiModelProperty(value = "登录类型")
    private int loginType;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    private Long id;

    private Integer status = 1;
}