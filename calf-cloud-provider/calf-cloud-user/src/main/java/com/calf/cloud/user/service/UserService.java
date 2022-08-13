

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

package com.calf.cloud.user.service;

import com.calf.cloud.user.pojo.vo.UserInfoVO;

/**
 * <pre>用户信息</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 18:11
 */
public interface UserService {

    UserInfoVO getUserByusername(String username);
}
