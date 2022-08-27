/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 21时03分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 21:03:43    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.pojo.dto;

import com.calf.cloud.dal.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoDTO extends BaseEntity {


    /**
     * 注册手机号
     */
    @NotBlank(groups = addGroup.class)
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

    private String identityCard;

    /**
     * 微信唯一标识
     */
    private String unionId;

    /**
     * 微信openID
     */
    private String openId;

    /**
     * 用户绑定微信的昵称，不可修改，用户换绑页面展示
     */
    private String wxNickname;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 苹果登陆唯一标识
     */
    private String signAppleId;

    public interface addGroup {
    }

    public interface editGroup {
    }

}
