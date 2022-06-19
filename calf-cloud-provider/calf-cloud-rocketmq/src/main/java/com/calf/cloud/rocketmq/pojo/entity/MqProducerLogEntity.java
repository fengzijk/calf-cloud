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

package com.calf.cloud.rocketmq.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <pre>功能描述:</pre>
 *
 * @author : guozhifeng
 * @date : 2022-04-16 13:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "mq_producer_record")
@Accessors(chain = true)
public class MqProducerLogEntity extends BaseEntity implements Serializable {
    /**
     * 投递MQ的topic
     */
    private String topic;
    /**
     * 消息的唯一业务标记，用作幂等处理，一般是业务ID或者业务唯一编码
     */
    private String refNo;
    /**
     * 标记，用作业务逻辑分类
     */
    private String tag;
    /**
     * tag名称
     */
    private String tagName;
    /**
     * 源服务
     */
    private String fromService;
    /**
     * 目标服务
     */
    private String toService;
    /**
     * 消息内容JSON
     */
    private String messageData;
    /**
     * 指定时间进行投递
     */
    private LocalDateTime startDeliverTime;
    /**
     * 执行次数
     */
    private Integer executeCount;
    /**
     * 执行结果
     */
    private String executeResult;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 发送标记
     */
    private Byte sendFlag;
}
