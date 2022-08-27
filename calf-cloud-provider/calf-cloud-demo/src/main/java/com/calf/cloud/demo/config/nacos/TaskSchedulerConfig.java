/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月27日 21时03分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-27 21:03:44    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.demo.config.nacos;

import com.calf.cloud.demo.config.MdcThreadPoolTaskScheduler;
import com.calf.cloud.starter.response.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <pre>定时任务线程池</pre>
 *
 * @author guozhifeng
 * @date 2022/8/13 23:56
 */
@Configuration
@Slf4j
public class TaskSchedulerConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(createDefaultTaskExecutor());
    }

    /**
     * 创建默认任务执行线程池
     */
    @Bean(destroyMethod = "shutdown")
    public Executor createDefaultTaskExecutor() {


        ThreadPoolTaskScheduler taskScheduler = new MdcThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("-default-task-pool-");
        // 拒绝策略：抛异常
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskScheduler.setErrorHandler(ex -> {
            boolean flag = ex instanceof BizException || ex.getCause() != null && ex.getCause() instanceof BizException;
            if (flag) {
                log.warn("TaskScheduler Error:" + ExceptionUtils.getStackTrace(ex));
            } else {
                log.error("TaskScheduler Error:" + ExceptionUtils.getStackTrace(ex));
            }
        });
        taskScheduler.initialize();
        return taskScheduler;
    }


    /**
     * 创建需要休眠的任务执行线程池，休眠会占用线程池资源，避免需要执行的线程被堵塞
     */
    @Bean(destroyMethod = "shutdown", name = "sleepTaskExecutor")
    public Executor createSleepTaskExecutor() {
        // 自定义线程个数，取范围4-32,由于自定义处理延时消息，很多任务在休眠，占用较多线程数量，所以范围要大些

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("-task-pool-");
        // 拒绝策略：抛异常
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskScheduler.setErrorHandler(ex -> {
            boolean flag = ex instanceof BizException || ex.getCause() != null && ex.getCause() instanceof BizException;
            if (flag) {
                log.warn("TaskScheduler Error:" + ExceptionUtils.getStackTrace(ex));
            } else {
                log.error("TaskScheduler Error:" + ExceptionUtils.getStackTrace(ex));
            }
        });
        taskScheduler.initialize();
        return taskScheduler;
    }
}
