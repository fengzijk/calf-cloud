/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 23时34分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 23:34:41    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.handle;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestHandleChain {

    /**
     * 处理集合
     */

    @Autowired
    private List<TestHandle> handlerList;


    public boolean execute(String userActionTypeCode) {

        if (CollectionUtils.isEmpty(handlerList)) {
            return false;
        }

        // 依次调用每个Handler:
        for (TestHandle handler : handlerList) {
            Boolean r = handler.execute(userActionTypeCode);
            if (BooleanUtils.isTrue(r)) {
                // 如果返回TRUE，处理结束:
                return r;
            }
        }
        return false;
    }
}
