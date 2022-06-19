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
 *  2021-10-05 17:46:34    fengzijk         1.0         Why & What is modified: 改原因描述>
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

    UserInfoVO getUserByUserName(String userName);
}
