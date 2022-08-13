
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

package com.calf.cloud.starter.redis.redission;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;

/**
 * <pre>分布式锁工具类</pre>
 *
 * @author : guozhifeng
 * @date : 2022/6/19 14:20
 */
public class RedissonLockUtil {
    private static DistributedLocker redisLock;

    public static void setLocker(DistributedLocker locker) {
        redisLock = locker;
    }

    public static RLock lock(String lockKey) {
        return redisLock.lock(lockKey);
    }

    public static void unlock(String lockKey) {
        redisLock.unlock(lockKey);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     * @return
     */
    public static RLock lock(String lockKey, int timeout) {
        return redisLock.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String lockKey, TimeUnit unit, int timeout) {
        return redisLock.lock(lockKey, unit, timeout);
    }
}
