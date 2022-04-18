package com.calf.cloud.rocketmq.service;


import com.calf.cloud.rocketmq.pojo.dto.SaveMqProducerLogDTO;
import com.calf.cloud.rocketmq.pojo.dto.UpdateMqProducerLogSendFlagDTO;
/**
*-------------------------------------------------
* <pre>功能描述:</pre>
* @author : guozhifeng
* @date : 2022-04-16 13:54
*--------------------------------------------------
*/
public interface MqProducerLogService {



    /**
     * 功能描述
     * @param saveMqProducerLogDTO ,
     * @return java.lang.Boolean
     * @author : fengzijk
     * @date : 2022-04-16 13:53
     */
    Long saveMqProducerLog(SaveMqProducerLogDTO saveMqProducerLogDTO);

    /**
     * 功能描述
     * @param updateMqProducerLogSendFlagDTO ,
     * @return java.lang.Boolean
     * @author : fengzijk
     * @date : 2022-04-16 13:53
     */
    Boolean updateMqProducerLog(UpdateMqProducerLogSendFlagDTO updateMqProducerLogSendFlagDTO);
}
