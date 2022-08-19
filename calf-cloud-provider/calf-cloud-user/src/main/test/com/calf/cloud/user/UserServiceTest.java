/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月13日 22时19分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-13 22:19:59    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.calf.cloud.starter.response.json.JsonUtil;
import com.calf.cloud.user.mapper.UserInfoMapper;
import com.calf.cloud.user.pojo.entity.UserInfoEntity;
import com.calf.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void save() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setPhone("23");
        userInfoEntity.setEmail("123@163.vcom");
        userInfoEntity.setUsername("2222");
        userInfoEntity.setNickname("1111");
        userInfoEntity.setSex(false);
        userInfoEntity.setSalt("22");
        userInfoEntity.setPassword("11");
        userInfoEntity.setAvatar("22");
        userInfoEntity.setIdentityCard("22");
        userInfoEntity.setUnionId("3");
        userInfoEntity.setOpenId("3");
        userInfoEntity.setWxNickname("3");
        userInfoEntity.setBirthday(LocalDateTime.now());
        userInfoEntity.setRegisterTime(LocalDateTime.now());
        userInfoEntity.setSignAppleId("1");
        userInfoEntity.setCreateId(0L);
        userInfoEntity.setUpdateId(0L);
        userInfoEntity.setCreateTime(LocalDateTime.now());
        userInfoEntity.setUpdateTime(LocalDateTime.now());
        userInfoEntity.setDeleteFlag(false);
        userInfoEntity.setStatus(false);
        userService.add(userInfoEntity);
        log.info("1111:{}", JsonUtil.tojson(userInfoEntity));
    }


    @Test
    void select() {

        LambdaQueryWrapper<UserInfoEntity> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.select(UserInfoEntity::getAvatar, UserInfoEntity::getEmail);
        userInfoMapper.selectList(lambdaQuery).forEach(a -> log.info("1111:{}", JsonUtil.tojson(a)));
    }


    @Test
    void select1() {

        LambdaQueryWrapper<UserInfoEntity> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.select(UserInfoEntity::getAvatar, UserInfoEntity::getEmail);
        userInfoMapper.selectList(lambdaQuery).forEach(a -> log.info("1111:{}", JsonUtil.tojson(a)));
    }


    @Test
    void select11() {

        userInfoMapper.selectList(new QueryWrapper<UserInfoEntity>().eq("password", "11")).forEach(a -> log.info("1111:{}", JsonUtil.tojson(a)));
    }

    @Test
    void select12() {
        //1、eq查询单条
        JsonUtil.tojson(new LambdaQueryChainWrapper<>(userInfoMapper).eq(UserInfoEntity::getNickname, "1111").last("limit 1").one());

        new LambdaQueryChainWrapper<>(userInfoMapper).eq(UserInfoEntity::getNickname, "1111").list().forEach(a -> log.info("1111:{}", JsonUtil.tojson(a)));

        //3、模糊查询
        new LambdaQueryChainWrapper<>(userInfoMapper).like(UserInfoEntity::getEmail, "test").list().forEach(a -> log.info("1111:{}", JsonUtil.tojson(a)));


    }
}
