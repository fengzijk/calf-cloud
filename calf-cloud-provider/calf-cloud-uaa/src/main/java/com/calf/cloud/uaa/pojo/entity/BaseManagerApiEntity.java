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

package com.calf.cloud.uaa.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 管理员接口表
 *
 * @author fengzijk
 * @date 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("base_manager_api")
@ApiModel(value = "管理员接口对象", description = "管理员接口表")
public class BaseManagerApiEntity extends BaseEntity {

    /**
     * 接口编码
     */
    private String code;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述
     */
    private String notes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 类名
     */
    @TableField("class_name")
    private String className;

    /**
     * 方法名
     */
    @TableField("method_name")
    private String methodName;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 响应类型
     */
    @TableField("content_type")
    private String contentType;

    /**
     * 服务ID
     */
    @TableField("service_id")
    private String serviceId;

    /**
     * 是否认证:0:不认证 1:认证
     */
    private Boolean auth;


}
