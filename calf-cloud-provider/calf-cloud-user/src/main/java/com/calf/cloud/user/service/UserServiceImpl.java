/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 18时09分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 18:09:40    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.user.mapper.UserInfoMapper;
import com.calf.cloud.user.pojo.entity.UserInfoEntity;
import com.calf.cloud.user.service.impl.UserService;
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

}
