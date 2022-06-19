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

package com.calf.cloud.rocketmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.rocketmq.mapper.MqProducerLogMapper;
import com.calf.cloud.rocketmq.pojo.dto.SaveMqProducerLogDTO;
import com.calf.cloud.rocketmq.pojo.dto.UpdateMqProducerLogSendFlagDTO;
import com.calf.cloud.rocketmq.pojo.entity.MqProducerLogEntity;
import com.calf.cloud.rocketmq.service.MqProducerLogService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <pre>功能描述:</pre>
 *
 * @author : guozhifeng
 * @date : 2022-04-16 15:47
 */
@Service
public class MqProducerLogServiceImpl extends ServiceImpl<MqProducerLogMapper, MqProducerLogEntity> implements MqProducerLogService {
    @Resource
    private MqProducerLogMapper mqProducerLogMapper;

    @Override
    public Long saveMqProducerLog(SaveMqProducerLogDTO saveMqProducerLogDTO) {
        MqProducerLogEntity mqProducerLog = ModelMapperUtil.map(saveMqProducerLogDTO, MqProducerLogEntity.class);

        mqProducerLogMapper.insert(mqProducerLog);
        return mqProducerLog.getId();
    }

    @Override
    public Boolean updateMqProducerLog(UpdateMqProducerLogSendFlagDTO updateMqProducerLogSendFlagDTO) {
        MqProducerLogEntity entity = ModelMapperUtil.map(updateMqProducerLogSendFlagDTO, MqProducerLogEntity.class);
        entity.setId(updateMqProducerLogSendFlagDTO.getId());
        entity.setUpdateId(0L);
        int result = mqProducerLogMapper.updateById(entity);
        return result > 0;
    }
}
