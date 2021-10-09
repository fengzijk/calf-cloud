/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 12时16分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 12:16:38    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.auth.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TokenEntity implements Serializable {

    /**
     * 唯一标识
     */
    private String id;
    /**
     * token
     */
    private String token;
    /**
     * 失效事件
     */
    private LocalDateTime invalidDate;
    /**
     * 失效 1 有效  0 无效
     */
    private Integer status = 1;
}