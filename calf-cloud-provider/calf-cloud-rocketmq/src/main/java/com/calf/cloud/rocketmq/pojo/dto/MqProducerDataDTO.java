package com.calf.cloud.rocketmq.pojo.dto;

import com.calf.cloud.roketmq.enums.TagEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@Accessors(chain = true)
public class MqProducerDataDTO implements Serializable {
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
    private String refNo;


    /**
     * 标记，用作业务逻辑分类
     */
    private TagEnum tag;


    /**
     * 指定时间进行投递
     */
    private LocalDateTime sendDateTime;



}
