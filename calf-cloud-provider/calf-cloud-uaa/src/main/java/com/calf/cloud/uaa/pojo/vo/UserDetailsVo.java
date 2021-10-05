/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月06日 01时35分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-06 01:35:57    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.pojo.vo;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author 郭志峰
 */
@Getter
public class UserDetailsVo  extends User {

    /**
     * 用户ID
     */
    private final Long id;

    /**
     * 部门ID
     */
    private final String roleId;
    /**
     * 部门ID
     */
    private final Long departId;

    /**
     * 手机号
     */
    private final String mobile;

    /**
     * 头像
     */
    private final String avatar;

    /**
     * 登录类型
     */
    private final int loginType;


    public UserDetailsVo(Long id, int loginType, Long departId, String roleId, String mobile, String avatar,String username,
      String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
      boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.id = id;
        this.loginType = loginType;
        this.roleId = roleId;
        this.departId = departId;
        this.mobile = mobile;
        this.avatar = avatar;
    }
}
