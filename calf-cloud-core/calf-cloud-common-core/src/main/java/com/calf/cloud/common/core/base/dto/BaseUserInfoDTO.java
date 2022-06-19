/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 11时21分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 11:21:40    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**

 * <pre>功能描述:</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 11:23

 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class BaseUserInfoDTO implements Serializable {

    private Long userId;
    private String userName;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;


}
