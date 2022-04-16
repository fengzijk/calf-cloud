/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年04月16日 15时41分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2022-04-16 15:41:30    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.rocketmq.controller;

import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.rocketmq.pojo.dto.MqProducerDataDTO;
import com.calf.cloud.rocketmq.service.impl.ProduceServiceImpl;
import com.calf.cloud.roketmq.dto.SendMqDTO;
import com.calf.cloud.starter.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
public class MqController{



    private final ProduceServiceImpl produceService;



    @Operation(summary = "发送MQ", description = "发送Mq", method = "POST")
    @PostMapping(value = "/send-to-mq")
    public ResponseResult<Boolean> sendToMq(@RequestBody @Valid SendMqDTO dto) {
        MqProducerDataDTO  dataDTO = ModelMapperUtil.map(dto, MqProducerDataDTO.class);
       return  new ResponseResult<Boolean>().setData(produceService.sendMsgToMq(dataDTO));
    }



}

