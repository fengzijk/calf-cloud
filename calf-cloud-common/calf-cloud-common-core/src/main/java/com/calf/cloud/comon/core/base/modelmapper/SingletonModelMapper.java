/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 01时29分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 01:29:04    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.base.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * -------------------------------------------------
 * <pre>ModelMapper单例</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 1:56
 * --------------------------------------------------
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