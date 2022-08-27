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

package com.calf.cloud.demo.config;

import com.calf.cloud.starter.response.exception.BizException;
import com.calf.cloud.starter.response.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * <pre>异步线程池配置</pre>
 *
 * @author guozhifeng
 * @date 2022/8/13 23:50
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncThreadPoolConfig implements AsyncConfigurer {


    @Override

    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程的最少数量
        executor.setCorePoolSize(5);
        // 线程的最大数量
        executor.setMaxPoolSize(10);
        // 缓冲队列 大小
        executor.setQueueCapacity(10);
        // 线程名字前缀
        executor.setThreadNamePrefix("-async-pool-");
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);

        // 注意这里父子线程传递MDC 遍历进行了装饰增强
        executor.setTaskDecorator(new MdcTaskDecorator());
        // 拒绝策略：抛异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            boolean flag = ex instanceof BizException || ex.getCause() != null && ex.getCause() instanceof BizException;
            if (flag) {
                log.warn("async method:{}, params:{}, \ne:{} ", JsonUtil.tojson(method), JsonUtil.tojson(params), ExceptionUtils.getStackTrace(ex));
            } else {
                log.error("async method:{}, params:{}, \ne:{} ", JsonUtil.tojson(method), JsonUtil.tojson(params), ExceptionUtils.getStackTrace(ex));
            }

        };
    }
}
