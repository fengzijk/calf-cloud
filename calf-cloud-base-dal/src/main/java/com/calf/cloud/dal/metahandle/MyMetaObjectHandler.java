/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 11时16分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 11:16:15    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.dal.metahandle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.calf.cloud.comon.core.base.dto.UserHolder;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * -------------------------------------------------
 * <pre>功能描述:</pre>
 *
 * @author : fengzijk
 * @date : 2021/8/9 下午8:23
 * --------------------------------------------------
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建人
     */
    private static final String CREATE_ID = "createId";
    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createTime";
    /**
     * 修改人
     */
    private static final String UPDATE_ID = "updateId";

    /**
     * 修改时间
     */
    private static final String UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        AtomicReference<Long> userId = new AtomicReference<>(1L);
        Optional.ofNullable(UserHolder.getCurrentUser()).ifPresent(u -> userId.set(u.getUserId()));
        this.fillStrategy(metaObject, CREATE_ID, userId.get());
        this.fillStrategy(metaObject, UPDATE_ID, userId.get());
        this.setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        AtomicReference<Long> userId = new AtomicReference<>(1L);
        Optional.ofNullable(UserHolder.getCurrentUser()).ifPresent(u -> userId.set(u.getUserId()));
        this.fillStrategy(metaObject, UPDATE_ID, userId.get());
        this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}