package com.calf.cloud.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calf.cloud.common.core.base.modelmapper.ModelMapperUtil;
import com.calf.cloud.uaa.mapper.BaseManagerMapper;
import com.calf.cloud.uaa.pojo.entity.BaseManagerEntity;
import com.calf.cloud.uaa.pojo.entity.UserInfoEntity;
import com.calf.cloud.uaa.pojo.vo.BaseManagerVO;
import com.calf.cloud.uaa.pojo.vo.UserInfoVO;
import com.calf.cloud.uaa.service.BaseManagerService;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author fengzijk
 * @since 2021-10-05
 */
@Service
public class BaseManagerServiceImpl extends ServiceImpl<BaseManagerMapper, BaseManagerEntity> implements BaseManagerService {

    public BaseManagerVO selectByUserId(Long userId) {
        LambdaQueryWrapper<BaseManagerEntity> lambda3 = Wrappers.lambdaQuery();
        lambda3.eq(BaseManagerEntity::getUserId, userId);
        BaseManagerEntity entity = super.baseMapper.selectOne(lambda3);

        return ModelMapperUtil.map(entity, BaseManagerVO.class);
    }
}
