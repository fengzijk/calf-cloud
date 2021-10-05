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
 * 客户端表
 *
 * @author fengzijk
 * @date 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "管理员客户端", description = "客户端表")
@TableName("base_manager_client")
public class BaseManagerClientEntity extends BaseEntity {
    /**
     * 客户端id
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * 资源集合
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 授权类型
     */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 权限
     */
    private String authorities;

    /**
     * 令牌过期秒数
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌过期秒数
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 附加说明
     */
    @TableField("additional_information")
    private String additionalInformation;

    /**
     * 自动授权
     */
    private String autoapprove;

}
