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
*-------------------------------------------------
* <pre>功能描述:</pre>
* @author : guozhifeng
* @date : 2022-04-16 15:47
*-------------------------------------------------- 
*/
@Service
public class MqProducerLogServiceImpl extends ServiceImpl<MqProducerLogMapper, MqProducerLogEntity> implements MqProducerLogService {
    @Resource
    private MqProducerLogMapper mqProducerLogMapper;

    @Override
    public Boolean saveMqProducerLog(SaveMqProducerLogDTO saveMqProducerLogDTO) {
        MqProducerLogEntity mqProducerLog = ModelMapperUtil.map(saveMqProducerLogDTO, MqProducerLogEntity.class);
        mqProducerLog.setCreateId(0L);
        mqProducerLog.setUpdateId(0L);
        int result = mqProducerLogMapper.insert(mqProducerLog);
        saveMqProducerLogDTO.setId(mqProducerLog.getId());
        return result>0;
    }

    @Override
    public Boolean updateMqProducerLog(UpdateMqProducerLogSendFlagDTO updateMqProducerLogSendFlagDTO) {
        MqProducerLogEntity mqProducerLog = ModelMapperUtil.map(updateMqProducerLogSendFlagDTO, MqProducerLogEntity.class);
        mqProducerLog.setUpdateId(0L);
        int result = mqProducerLogMapper.updateById (mqProducerLog);
        return result>0;
    }
}
