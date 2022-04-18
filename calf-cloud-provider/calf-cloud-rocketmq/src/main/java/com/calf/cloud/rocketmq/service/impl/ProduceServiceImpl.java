/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年04月16日 15时51分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2022-04-16 15:51:01    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.rocketmq.service.impl;


import com.calf.cloud.rocketmq.pojo.dto.MqProducerDataDTO;
import com.calf.cloud.rocketmq.pojo.dto.SaveMqProducerLogDTO;
import com.calf.cloud.rocketmq.pojo.dto.UpdateMqProducerLogSendFlagDTO;
import com.calf.cloud.rocketmq.service.MqProducerLogService;
import com.calf.cloud.roketmq.enums.TagEnum;
import com.calf.cloud.starter.response.exception.BusinessException;
import com.calf.cloud.starter.response.json.JsonUtil;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import lombok.val;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


/**
 * @author 郭志峰
 */
@Service
@Slf4j
public class ProduceServiceImpl {

    @Autowired
    private RocketMQTemplate rocketMqTemplate;
    @Autowired
    private MqProducerLogService mqProducerLogService;


    /**
     * 发生MQ消息，支持延迟消费
     *
     * @param dto
     * @return 发送结果
     */
    public Boolean sendMsgToMq(MqProducerDataDTO dto) {
        long start = System.currentTimeMillis();
        dto.setTag(TagEnum.HELLO_WORLD);
        log.info("MQ开始生产:{}", dto);

        TagEnum tagEnum = dto.getTag();
        //校验基础参数

        //将数据保存到Mysql，用于消息补偿达到消息100%到达MQ Broker
        SaveMqProducerLogDTO saveMqProducerLogDTO;
        try {
            saveMqProducerLogDTO = new SaveMqProducerLogDTO()
              .setTopic(dto.getTopic())
              .setRefNo(dto.getRefNo())
              .setMessageData(dto.getMessageData());
            if (Objects.nonNull(tagEnum)) {
                saveMqProducerLogDTO
                  .setTag(tagEnum.getTag())
                  .setFromService(tagEnum.getFromService())
                  .setToService(tagEnum.getToService())
                  .setTagName(tagEnum.getDesc());
            }

            Long id = mqProducerLogService.saveMqProducerLog(saveMqProducerLogDTO);
            saveMqProducerLogDTO.setId(id);
        } catch (Exception e) {
            log.error("MQ消息保存失败,异常信息:{}", e.getMessage(), e);
            //一定要保证异常抛出，进而使业务代码回滚
            throw new BusinessException("MQ发送失败");
        }

        SendResult sendResult = sendMq(dto);

        if (Objects.nonNull(sendResult) && sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
            //MQ投递成功
            UpdateMqProducerLogSendFlagDTO updateMqProducerLogSendFlagDTO = new UpdateMqProducerLogSendFlagDTO();
            updateMqProducerLogSendFlagDTO.setId(saveMqProducerLogDTO.getId());
            updateMqProducerLogSendFlagDTO.setSendFlag(true);
            updateMqProducerLogSendFlagDTO.setExecuteTime(LocalDateTime.now());
            updateMqProducerLogSendFlagDTO.setExecuteResult(JsonUtil.tojson(sendResult));
            mqProducerLogService.updateMqProducerLog(updateMqProducerLogSendFlagDTO);
        }
        log.info("MQ消息生产结束,耗时:{}ms", System.currentTimeMillis() - start);
        return true;

    }


    /**
     * 发送MQ
     * 延迟消费阿里云最长支持40天，如果超过40天，本次MQ不进行投递，由JOB程序进行处理
     */
    private SendResult sendMq(MqProducerDataDTO mqProducerDataDTO) {
//        //将消息投递阿里云MQ Broker，以下逻辑允许失败，通过Job补偿机制实现
//        if (Objects.nonNull(mqProducerDataDTO.getSendDateTime())) {
//            long betweenDays = DateUtil.get(LocalDateTime.now(), mqProducerDataDTO.getSendDateTime());
//            if (betweenDays >= 30) {
//                log.info("延迟消息过长，不进行投递");
//                return null;
//            }
//        }

        Message message = new Message(mqProducerDataDTO.getTopic(), mqProducerDataDTO.getTag().getTag(),
          mqProducerDataDTO.getMessageData().getBytes(StandardCharsets.UTF_8));

        //业务唯一标识
        //延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递
//            if (sendDateTime != null) {
//                long producerTime = DateUtil.localDateTime2Timestamp(sendDateTime);
//                mqProducerDataDTO.setStartDeliverTime(producerTime);
//            }
        // 同步发送消息，只要不抛异常就是成功

        SendResult sendResult = rocketMqTemplate.syncSend(mqProducerDataDTO.getTopic(), message);
        log.info("MQ消息生产：{}", JsonUtil.tojson(message));
        log.info("MQ消息生产成功,返回结果:{}", JsonUtil.tojson(sendResult));

        return sendResult;
    }


}
