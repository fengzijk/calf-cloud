/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 00时42分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 00:42:27    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**

 * <pre>时间处理工具类</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 0:49

 */
public class DateUtil {


    /**
     * 获得当月最小时间 2019-04-30 00:00:00
     *
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getMounthStartTime(LocalDateTime localDateTime) {
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return startOfDay.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获得当天最小时间 2019-04-30 00:00:00
     *
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getDayStartTime(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MIN);
    }

    /**
     * 获得当月最小时间 2019-04-30 00:00:00
     *
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getDayEndTime(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MAX);
    }

    /**
     * 获得当天最小时间 2019-04-30 00:00:00
     *
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getDayStartTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 获得当天最小时间 2019-04-30 00:00:00
     *
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getDayEndTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MAX);
    }

    /**
     * 将Date转换为LocalDateTime
     *
     * @param date java.util.Date类型的参数
     * @return java.time.LocalDateTime
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 时间戳 转 LocalDateTime
     *
     * @param timestamp 时间戳
     * @return 返回LocalDateTime
     */
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }


    /**
     * <pre> 毫秒数转LocalDateTime</pre>
     *
     * @param str str 毫秒数字符串
     * @return java.time.LocalDateTime
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime timeMillis2LocalDateTime(String str) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(str));
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * LocalDateTime 转 时间戳
     *
     * @param localDateTime LocalDateTime 类型参数
     * @return 返回时间戳
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 时间转为指定格式的字符串
     *
     * @param time LocalDateTime 类型的参数
     * @param pattern 需要转换的格式类型
     * @return 返回String类型
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static String time2FormatString(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 时间转为指定格式的字符串
     *
     * @param time LocalDateTime 类型的参数
     * @param pattern 需要转换的格式类型
     * @return 返回String类型
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static String time2FormatString(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 指定格式的字符串转为时间
     *
     * @param formatString 字符串
     * @param pattern 需要转换的格式类型
     * @return 返回  LocalDateTime 类型
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime formatString2Time(String formatString, String pattern) {
        return LocalDateTime.parse(formatString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 指定格式的字符串转为时间
     *
     * @param formatString 字符串
     * @param pattern 需要转换的格式类型
     * @return 返回  LocalDateTime 类型
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalTime formatString2LocalTime(String formatString, String pattern) {
        return LocalTime.parse(formatString, DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 获取指定时间加另一个时间后的时间
     *
     * @param number 要加的数字
     * @param unit 单位 eg:ChronoUnit.SECONDS
     * @return LocalDateTime LocalDateTime
     * @author lch
     * @date 2019-06-11
     */
    public static LocalDateTime getTimeAddition(LocalDateTime time, long number, TemporalUnit unit) {
        return time.plus(number, unit);
    }

    /**
     * 获取当前时间减去另一个时间后的时间
     *
     * @param number 要加的数字
     * @param unit 单位 eg:ChronoUnit.SECONDS
     * @return LocalDateTime LocalDateTime
     * @author : fengzijk
     * @date : 2019/5/7 17:04
     */
    public static LocalDateTime getNowMinus(long number, TemporalUnit unit) {
        return LocalDateTime.now().minus(number, unit);
    }


    /**
     * <pre> Date类型格式化LocalDateTime  </pre>
     *
     * @param date data 类型参数
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime formartDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }


}
