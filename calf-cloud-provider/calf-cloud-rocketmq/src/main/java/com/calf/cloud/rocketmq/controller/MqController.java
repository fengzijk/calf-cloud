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

package com.calf.cloud.rocketmq.controller;

import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.rocketmq.pojo.dto.MqProducerDataDTO;
import com.calf.cloud.rocketmq.service.impl.ProduceServiceImpl;
import com.calf.cloud.roketmq.dto.SendMqDTO;
import com.calf.cloud.starter.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class MqController {


    private final ProduceServiceImpl produceService;


    @PostMapping(value = "/send-to-mq")
    public ResponseResult<Boolean> sendToMq(@RequestBody @Valid SendMqDTO dto) {
        MqProducerDataDTO dataDTO = ModelMapperUtil.mapClass(dto, MqProducerDataDTO.class, (s, t) -> t.setMessageData(s.getMessageData()));
        return new ResponseResult<Boolean>().setData(produceService.sendMsgToMq(dataDTO));
    }


}

