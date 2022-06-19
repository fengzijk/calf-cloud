
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
 * @ProjectName : redission-spring-boot-starter
 * @Descprition : 定义锁的接口
 * @Author : fengzijk
 * @email: guozhifengvip@163.com
 * @Time : 2019/3/23 10:54
 */
public interface DistributedLocker {

    /**
     * 此方法不带超时 不手动释放可能引起死锁
     *
     * @param lockKey redis的key
     * @return RLock  返回 com.fengzijk.redisson.lock对象
     * @author : fengzijk
     * @date : 2019/4/24 13:52
     */
    RLock lock(String lockKey);

    /**
     * 此方法带超时 单位为毫秒
     *
     * @param lockKey redis的key
     * @param timeout 超时时间，单位为秒
     * @return RLock  返回 com.fengzijk.redisson.redlock对象
     * @description : 带redisKey以及超时的锁
     * @methodName :lock
     * @author : fengzijk
     * @date : 2019/4/24 13:56
     */
    RLock lock(String lockKey, int timeout);


    /**
     * @param lockKey key
     * @param unit    时间单位（建议不要太长）
     * @param timeout 超时释放时间
     * @return RLock  返回 com.fengzijk.redisson.redlock对象
     * @description :
     * @methodName :
     * @author : fengzijk
     * @date : 2019/4/24 14:04
     */

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     * 加锁操作 支持过期解锁功能,最多等待多久就上锁leaseTime后以后自动解锁, 无需调用unlock方法手动解锁
     *
     * @param lockKey   redis key
     * @param unit      时间单位
     * @param waitTime  等待时间数值
     * @param leaseTime 经过多久释放的数值
     * @return boolean 加锁是否成功
     * @methodName :tryLock
     * @author : fengzijk
     * @date : 2019/4/24 14:08
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     * 通过redisKey 释放锁
     *
     * @param lockKey rediskey
     * @return void
     * @author : fengzijk
     * @date : 2019/4/24 14:37
     */
    void unlock(String lockKey);

    /**
     * 通过所对象释放锁
     *
     * @param lock
     * @return void
     * @author : fengzijk
     * @date : 2019/4/24 14:44
     */
    void unlock(RLock lock);
}
