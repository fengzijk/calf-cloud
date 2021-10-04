/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 01时43分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 01:43:01    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.base.genId;

import com.calf.cloud.comon.core.utils.IpUtils;

/**
 * -------------------------------------------------
 * <pre>雪花算法生成id</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 2:04
 * --------------------------------------------------
 */
public class SnowflakeIdWorker {

    private static SnowflakeIdWorker idWorker = null;

    public static SnowflakeIdWorker getInstance() {
        if (null == idWorker) {
            try {
                Long[] dataId = dataId();
                idWorker = new SnowflakeIdWorker(dataId[1], dataId[0]);
            } catch (Exception e) {
                idWorker = new SnowflakeIdWorker(1, 1);
            }
        }
        return idWorker;
    }

    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1489111610226L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId 工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
              String.format("Clock moved backwards.  Refusing to generate courseId for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
          | (dataCenterId << dataCenterIdShift)
          | (workerId << workerIdShift)
          | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static Long genId() {
        return SnowflakeIdWorker.getInstance().nextId();
    }

    private static Long[] dataId() {
        String localHostIpString = IpUtils.getIp();
        String[] array = localHostIpString.split("\\.");
        long sum = 0;
        for (String s : array) {
            sum += Long.parseLong(s);
        }
        long max = 1 << 5;
        long dataCenterId = sum / max;
        long workId = sum % max;
        return new Long[]{dataCenterId, workId};
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws InterruptedException {
       /* Set<Long> set = new HashSet<>();
        System.out.println(System.currentTimeMillis());
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            long courseId = idWorker.nextId();
            set.add(courseId);
            System.out.println(courseId);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000 + "ms");
        System.out.println(set.size());*/
        // for (int i = 0; i < 10; i++) {
        System.out.println(SnowflakeIdWorker.genId());
        //}
    }
}