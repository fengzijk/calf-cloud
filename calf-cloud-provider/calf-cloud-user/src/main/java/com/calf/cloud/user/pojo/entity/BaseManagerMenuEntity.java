/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月06日 00时03分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-06 00:03:09    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 系统资源-菜单信息
 *
 * @author fengzijk
 * @date 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("base_manager_menu")
public class BaseManagerMenuEntity extends BaseEntity {

    /**
     * 父级菜单
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单编码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 描述
     */
    @TableField("menu_desc")
    private String menuDesc;

    /**
     * 路径前缀
     */
    private String scheme;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 菜单标题
     */
    private String icon;

    /**
     * 打开方式:_self窗口内,_blank新窗口
     */
    private String target;

    /**
     * 优先级 越小越靠前
     */
    private Long priority;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    @TableField("is_persist")
    private Boolean persist;

    /**
     * 服务名
     */
    @TableField("service_id")
    private String serviceId;


}
