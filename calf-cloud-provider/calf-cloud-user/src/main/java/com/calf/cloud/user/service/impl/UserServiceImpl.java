/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 17时46分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 17:46:30    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.user.mapper.UserInfoMapper;
import com.calf.cloud.user.pojo.entity.UserInfoEntity;
import com.calf.cloud.user.pojo.vo.BaseManagerVO;
import com.calf.cloud.user.pojo.vo.UserInfoVO;
import com.calf.cloud.user.service.BaseManagerService;
import com.calf.cloud.user.service.UserService;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * -------------------------------------------------
 * <pre>y</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:09
 * --------------------------------------------------
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserService {

    @Autowired
    private BaseManagerService baseManagerService;

    @Override
    public UserInfoVO getUserByUserName(String username) {
        LambdaQueryWrapper<UserInfoEntity> lambda3 = Wrappers.lambdaQuery();
        lambda3.eq(UserInfoEntity::getUsername, username);
        UserInfoEntity entity = super.baseMapper.selectOne(lambda3);
        UserInfoVO userInfoVO = ModelMapperUtil.map(entity, UserInfoVO.class);
        if (Objects.nonNull(entity)) {
            BaseManagerVO managerVO = baseManagerService.selectByUserId(entity.getId());
            userInfoVO.setBaseManagervo(managerVO);
            userInfoVO.setRoleIds(Arrays.asList("ADMIN"));
        }

        return userInfoVO;
    }
}
