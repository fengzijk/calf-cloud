package com.calf.cloud.user.service;


import com.calf.cloud.user.pojo.vo.BaseManagerVO;

/**
 * 服务类
 *
 * @author fengzijk
 * @since 2021-10-05
 */
public interface BaseManagerService {

    BaseManagerVO selectByUserId(Long userId);
}
