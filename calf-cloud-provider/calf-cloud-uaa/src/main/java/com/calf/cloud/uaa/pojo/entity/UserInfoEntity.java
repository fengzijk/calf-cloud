package com.calf.cloud.uaa.pojo.entity;


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
     * 主键
     */
    private Long id;

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
