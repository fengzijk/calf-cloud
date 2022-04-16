package com.calf.cloud.rocketmq.pojo.dto;


import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
*-------------------------------------------------
* <pre>功能描述:</pre>
* @author : guozhifeng
* @date : 16/4/2022 下午1:26
*-------------------------------------------------- 
*/
@Data
@Accessors(chain = true)
public class SaveMqProducerLogDTO implements Serializable {
    private Long id;
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

}
