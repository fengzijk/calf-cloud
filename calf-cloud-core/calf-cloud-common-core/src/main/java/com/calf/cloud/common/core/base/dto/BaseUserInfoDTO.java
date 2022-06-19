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
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
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
