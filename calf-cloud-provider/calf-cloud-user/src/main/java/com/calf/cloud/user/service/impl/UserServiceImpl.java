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

package com.calf.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.user.mapper.UserInfoMapper;
import com.calf.cloud.user.pojo.dto.UserInfoDTO;
import com.calf.cloud.user.pojo.entity.UserInfoEntity;
import com.calf.cloud.user.pojo.vo.UserInfoVO;
import com.calf.cloud.user.service.BaseManagerService;
import com.calf.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Objects;

/**
 * <pre>y</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserService {

    @Autowired
    private BaseManagerService baseManagerService;

    @Override
    public UserInfoVO getUserByUsername(String username) {
        LambdaQueryWrapper<UserInfoEntity> lambda3 = Wrappers.lambdaQuery();
        lambda3.eq(UserInfoEntity::getNickname, username);
        UserInfoEntity entity = super.baseMapper.selectOne(lambda3);
        UserInfoVO userInfoVO = ModelMapperUtil.map(entity, UserInfoVO.class);
        if (Objects.nonNull(entity)) {
            // BaseManagerVO managerVO = baseManagerService.selectByUserId(entity.getId());
            //   userInfoVO.setBaseManagervo(managerVO);
            userInfoVO.setRoleIds(Arrays.asList("ADMIN"));
        }

        return userInfoVO;
    }

    public Boolean add(UserInfoEntity userInfoEntity) {
        return super.save(userInfoEntity);
    }

    @Override
    public Boolean save(@Validated UserInfoDTO infoDTO) {
        System.out.println(11111);
        return null;
    }

    @Override
    public Boolean edit(UserInfoDTO infoDTO) {
        return null;
    }
}
