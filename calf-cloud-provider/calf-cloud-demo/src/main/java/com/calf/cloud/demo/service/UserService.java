

/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 21时56分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 21:56:18    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.service;

import com.calf.cloud.demo.pojo.dto.UserInfoDTO;
import com.calf.cloud.demo.pojo.entity.UserInfoEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <pre>用户信息</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:11
 */
@Validated(value = UserInfoDTO.addGroup.class)
public interface UserService {


    Boolean add(UserInfoEntity userInfoEntity);


    Boolean save(@Valid UserInfoDTO infoDTO);

    Boolean edit(@Valid UserInfoDTO infoDTO);

    List<UserInfoEntity> findByName(String name);
}
