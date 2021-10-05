/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月06日 01时42分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-06 01:42:29    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.handle.user;

import com.calf.cloud.common.core.enums.user.UserStatusEnum;
import com.calf.cloud.common.core.utils.PubUtils;
import com.calf.cloud.starter.response.exception.BusinessException;
import com.calf.cloud.uaa.pojo.vo.BaseManagerVO;
import com.calf.cloud.uaa.pojo.vo.UserDetailsVo;
import com.calf.cloud.uaa.pojo.vo.UserInfoVO;
import java.util.Collection;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * -------------------------------------------------
 * <pre>用户信息与授权对象转换</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/6 2:09
 * --------------------------------------------------
 */
@Component
@Slf4j
public class UserConversionHandle {

    public UserDetails userCoversUserDetailsVO(UserInfoVO userInfoVO) {
        if (Objects.isNull(userInfoVO)) {
            throw new BusinessException("该用户不存在");
        } else if (UserStatusEnum.DISABLE.getCode().equals(userInfoVO.getStatus())) {
            throw new BusinessException("对不起，您的账号：" + userInfoVO.getUsername() + " 已停用");
        }
        BaseManagerVO baseManagerVO = userInfoVO.getBaseManagervo();
        log.info("用户名：{}", baseManagerVO.getAccount());
        Collection<? extends GrantedAuthority> authorities
          = AuthorityUtils.createAuthorityList(PubUtils.toStrArray(userInfoVO.getRoleIds()));
        log.info("authorities: {}", authorities);
        return new UserDetailsVo(baseManagerVO.getId(), userInfoVO.getLoginType(), baseManagerVO.getDepartId(), baseManagerVO.getRoleId(),
          baseManagerVO.getMobile(),
          baseManagerVO.getAvatar(),
          userInfoVO.getUsername(), baseManagerVO.getPassword(), UserStatusEnum.ENABLE.getCode().equals(baseManagerVO.getStatus()),
          true, true, true,
          authorities);
    }

}
