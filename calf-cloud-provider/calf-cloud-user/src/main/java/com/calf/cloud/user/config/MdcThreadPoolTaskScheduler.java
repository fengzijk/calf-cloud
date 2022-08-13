/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月13日 23时41分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-13 23:41:36    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.user.config;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * <pre>
 * 任务调度池对象
 * 增强处理定时任务父子线程MDC传值
 * </pre>
 *
 * @author : guozhifeng
 * @date : 2022/6/21 11:41
 */
@SuppressWarnings("NullableProblems")
public class MdcThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

    /**
     *
     */
    private static final long serialVersionUID = 3363228283271090274L;

    /**
     * <pre>
     * 父子线程的MDC传递
     * </pre>
     *
     * @param runnable 执行器
     * @param context  上下文
     * @return java.lang.Runnable
     * @author : guozhifeng
     * @date : 2022/6/21 11:45
     */
    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            // 获取MDC
            Map<String, String> previous = MDC.getCopyOfContextMap();
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            try {
                runnable.run();
            } finally {
                if (previous == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(previous);
                }
            }
        };
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Instant startTime) {
        return super.schedule(wrap(task, MDC.getCopyOfContextMap()), startTime);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Instant startTime, Duration period) {
        return super.scheduleAtFixedRate(wrap(task, MDC.getCopyOfContextMap()), startTime, period);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Duration period) {
        return super.scheduleAtFixedRate(wrap(task, MDC.getCopyOfContextMap()), period);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Instant startTime, Duration delay) {
        return super.scheduleWithFixedDelay(wrap(task, MDC.getCopyOfContextMap()), startTime, delay);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Duration delay) {
        return super.scheduleWithFixedDelay(wrap(task, MDC.getCopyOfContextMap()), delay);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, MDC.getCopyOfContextMap()));
    }
}
