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

package com.calf.cloud.roketmq.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <pre>功能描述:</pre>
 *
 * @author : guozhifeng
 * @date : 2022-04-16 23:17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SendMqDTO implements Serializable {
    /**
     * 投递MQ的topic
     */
    private String topic;
    /**
     * 消息内容JSON
     */
    private String messageData;
    /**
     * 消息的唯一业务标记，用作幂等处理，一般是业务ID或者业务唯一编码
     */
    private String BizId;


    /**
     * 标记，用作业务逻辑分类
     */
    private String tag;


    /**
     * 指定时间进行投递
     */
    private LocalDateTime sendDateTime;


}
