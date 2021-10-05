/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时30分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:30:57    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.starter.security.service;

import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

/**
 * -------------------------------------------------
 * <pre>权限判断</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/5 15:31
 * --------------------------------------------------
 */

@Slf4j
@Service(value = "calfCloud")
public class PermissionService {

    public boolean hasPerm(String... permissions) {
        if (ArrayUtils.isEmpty(permissions)) {
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream()
          .map(GrantedAuthority::getAuthority)
          .filter(StringUtils::hasText)
          .anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
    }
}