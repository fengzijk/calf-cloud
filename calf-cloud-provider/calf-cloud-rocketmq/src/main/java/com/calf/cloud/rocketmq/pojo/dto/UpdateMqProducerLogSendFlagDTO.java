package com.calf.cloud.rocketmq.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UpdateMqProducerLogSendFlagDTO implements Serializable {
    private Long id;
    private String executeResult;
    private LocalDateTime executeTime;
    private Boolean sendFlag;
}
