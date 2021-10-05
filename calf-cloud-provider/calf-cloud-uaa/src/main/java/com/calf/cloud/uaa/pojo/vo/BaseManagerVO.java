/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月06日 00时10分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-06 00:10:59    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * -------------------------------------------------
 * <pre>系统用户VO</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/6 0:11
 * --------------------------------------------------
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseManagerVO implements Serializable {

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String name;

    /**
     * 真名
     */
    @ApiModelProperty(value = "真名")
    private String realName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String telephone;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private Long departId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departName;

    /**
     * 状态名称
     */

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    /**
     * 权限名称
     */

    @ApiModelProperty(value = "权限名称")
    private String roleName;

}
