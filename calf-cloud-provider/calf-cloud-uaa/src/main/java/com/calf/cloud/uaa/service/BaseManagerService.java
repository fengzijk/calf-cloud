package com.calf.cloud.uaa.service;


import com.calf.cloud.uaa.pojo.vo.BaseManagerVO;

/**
 * 服务类
 *
 * @author fengzijk
 * @since 2021-10-05
 */
public interface BaseManagerService {

    BaseManagerVO selectByUserId(Long userId);
}
