/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年06月19日 13时33分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-06-19 13:33:40    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.user.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author fengzijk
 * @date 2021-10-05
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
public class UserInfoEntity extends BaseEntity {


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

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 身份证号
     */
    @TableField("identity_card")
    private String identityCard;

    /**
     * 微信唯一标识
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 微信openID
     */
    @TableField("open_id")
    private String openId;

    /**
     * 用户绑定微信的昵称，不可修改，用户换绑页面展示
     */
    @TableField("wx_nickname")
    private String wxNickname;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 注册时间
     */
    @TableField("register_time")
    private LocalDateTime registerTime;

    /**
     * 苹果登陆唯一标识
     */
    @TableField("sign_apple_id")
    private String signAppleId;

}
