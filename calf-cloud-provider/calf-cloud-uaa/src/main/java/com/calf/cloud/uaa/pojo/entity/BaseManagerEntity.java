package com.calf.cloud.uaa.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author fengzijk
 * @since 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("base_manager")
public class BaseManagerEntity extends BaseEntity {

    /**
     * 账号
     */
    private String account;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 部门id
     */
    @TableField("depart_id")
    private Long departId;


}
