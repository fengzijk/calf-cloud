/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月13日 23时40分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-13 23:40:55    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.user.config;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

/**
 * <pre>
 * 异步线程池增强,MDC传值
 * </pre>
 *
 * @author : guozhifeng
 * @date : 2022/6/21 11:52
 */
@SuppressWarnings("NullableProblems")
public class MdcTaskDecorator implements TaskDecorator {


    /**
     * <pre>
     * 使异步线程池获得主线程的上下文,MDC传值
     * </pre>
     *
     * @param runnable 执行器
     * @return java.lang.Runnable
     * @author : guozhifeng
     * @date : 2022/6/21 11:50
     */
    public static Runnable runnable(Runnable runnable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(context);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

    /**
     * <pre>
     * 使异步线程池获得主线程的上下文,MDC传值
     * </pre>
     *
     * @param runnable 执行器
     * @return java.lang.Runnable
     * @author : guozhifeng
     * @date : 2022/6/21 11:51
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(context);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
