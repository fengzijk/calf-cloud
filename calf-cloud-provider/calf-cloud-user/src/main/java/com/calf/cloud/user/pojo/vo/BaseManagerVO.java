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

package com.calf.cloud.user.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**

 * <pre>系统用户VO</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/6 0:11

 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BaseManagerVO implements Serializable {

    /**
     * 主键id
     */
  @Schema(title = "主键id")
    private Long id;


    /**
     * 用户id
     */
  @Schema(title = "用户Id")
    private Long userId;


    /**
     * 账号
     */
  @Schema(title = "账号")
    private String account;

    /**
     * 密码
     */
  @Schema(title = "密码")
    private String password;

    /**
     * 昵称
     */
  @Schema(title = "昵称")
    private String name;

    /**
     * 真名
     */
  @Schema(title = "真名")
    private String realName;

    /**
     * 头像
     */
  @Schema(title = "头像")
    private String avatar;

    /**
     * 邮箱
     */
  @Schema(title = "邮箱")
    private String email;

    /**
     * 手机
     */
  @Schema(title = "手机")
    private String mobile;

    /**
     * 生日
     */
  @Schema(title = "生日")
    private LocalDateTime birthday;

    /**
     * 性别
     */
  @Schema(title = "性别")
    private Integer sex;

    /**
     * 角色id
     */
  @Schema(title = "角色id")
    private String roleId;

    /**
     * 部门id
     */
  @Schema(title = "部门id")
    private Long departId;

    /**
     * 状态
     */
  @Schema(title = "状态")
    private Integer status;

    /**
     * 删除标识
     */
  @Schema(title = "删除标识")
    private String isDeleted;

    /**
     * 备注
     */
  @Schema(title = "备注")
    private String remark;

    /**
     * 部门名称
     */
  @Schema(title = "部门名称")
    private String departName;

    /**
     * 状态名称
     */

  @Schema(title = "状态名称")
    private String statusName;

    /**
     * 权限名称
     */

  @Schema(title = "权限名称")
    private String roleName;

}
