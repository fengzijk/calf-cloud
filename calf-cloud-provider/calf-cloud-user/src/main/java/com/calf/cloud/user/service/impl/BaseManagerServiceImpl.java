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

package com.calf.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.user.mapper.BaseManagerMapper;
import com.calf.cloud.user.pojo.entity.BaseManagerEntity;
import com.calf.cloud.user.pojo.vo.BaseManagerVO;
import com.calf.cloud.user.service.BaseManagerService;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author fengzijk
 * @since 2021-10-05
 */
@Service
public class BaseManagerServiceImpl extends ServiceImpl<BaseManagerMapper, BaseManagerEntity> implements BaseManagerService {


    @Override
    public BaseManagerVO selectByUserId(Long userId) {
        LambdaQueryWrapper<BaseManagerEntity> lambda3 = Wrappers.lambdaQuery();
        lambda3.eq(BaseManagerEntity::getUserId, userId);
        BaseManagerEntity entity = super.baseMapper.selectOne(lambda3);

        return ModelMapperUtil.map(entity, BaseManagerVO.class);
    }
}
