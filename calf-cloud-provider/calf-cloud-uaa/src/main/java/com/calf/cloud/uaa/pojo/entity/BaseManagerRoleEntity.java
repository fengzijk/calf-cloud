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


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色表
 *
 * @author fengzijk
 * @date 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@TableName("base_manager_role")
public class BaseManagerRoleEntity extends BaseEntity {

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
	
    /**
     * 角色编码
     */
	@TableField("role_code")
	private String roleCode;
	
    /**
     * 描述
     */
	private String description;
	
    /**
     * 排序
     */
	private Integer sort;
	


}
