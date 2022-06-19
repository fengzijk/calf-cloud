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
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * <pre>ModelMapper单例</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 1:56
 */
public class SingletonModelMapper {

    private static volatile ModelMapper instance = null;

    private SingletonModelMapper() {
    }

    public static ModelMapper getInstance() {
        if (instance == null) {
            synchronized (SingletonModelMapper.class) {
                if (instance == null) {
                    instance = new ModelMapper();
                    instance.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                }
            }
        }
        return instance;
    }
}
