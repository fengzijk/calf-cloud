/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 21时32分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 21:32:33    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.demo.mapper.UserInfoMapper;
import com.calf.cloud.demo.pojo.dto.UserInfoDTO;
import com.calf.cloud.demo.pojo.entity.UserInfoEntity;
import com.calf.cloud.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * <pre></pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserService {


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
