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

package com.calf.cloud.rocketmq.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UpdateMqProducerLogSendFlagDTO implements Serializable {
    private Long id;
    private String executeResult;
    private LocalDateTime executeTime;
    private Boolean sendFlag;
}
