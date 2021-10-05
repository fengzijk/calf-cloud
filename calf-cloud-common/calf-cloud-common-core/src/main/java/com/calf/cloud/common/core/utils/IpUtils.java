/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 01时44分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 01:44:29    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class IpUtils {

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (StringUtils.isNotBlank(ip)) {
                ip = ip.split(",")[0];
            }
        } catch (Exception ignored) {
        }
        return ip;
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static String getIp() {
        String host;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            host = "192.168.48.198";
        }

        return host;
    }

    /*验证IP是否属于某个IP段
     *
     * ipSection    IP段（以'-'分隔）
     * ip           所验证的IP号码
     *
     */

    /**
     * 验证IP是否属于某个IP段
     *
     * ipSection    IP段（以'-'分隔）
     * ip           所验证的IP号码
     *
     * @return boolean
     * @author : fengzijk
     * @date : 2021/10/3 1:44
     */
    public static boolean ipExistsInRange(String ip, String ipSection) {

        ipSection = ipSection.trim();

        ip = ip.trim();

        int idx = ipSection.indexOf('-');

        String beginIp = ipSection.substring(0, idx);

        String endIp = ipSection.substring(idx + 1);

        return getIp2long(beginIp) <= getIp2long(ip) && getIp2long(ip) <= getIp2long(endIp);

    }

    public static long getIp2long(String ip) {

        ip = ip.trim();

        String[] ips = ip.split("\\.");

        long ip2long = 0L;

        for (int i = 0; i < 4; ++i) {

            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);

        }

        return ip2long;

    }

    public static long getIp2long2(String ip) {

        ip = ip.trim();

        String[] ips = ip.split("\\.");

        long ip1 = Integer.parseInt(ips[0]);

        long ip2 = Integer.parseInt(ips[1]);

        long ip3 = Integer.parseInt(ips[2]);

        long ip4 = Integer.parseInt(ips[3]);

        return ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;

    }


    /**
     * 比较IP大小
     *
     * @param ip1 ip1
     * @param ip2 ip2
     * @return int
     * @author : fengzijk
     * @date : 2021/10/3 1:46
     */
    public static int compareIpV4s(String ip1, String ip2) {
        int result = 0;
        // 获取ip1的32bit值
        int ipValue1 = getIpV4Value(ip1);
        // 获取ip2的32bit值
        int ipValue2 = getIpV4Value(ip2);
        if (ipValue1 > ipValue2) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }

    public static int getIpV4Value(String ipOrMask) {
        byte[] addr = getIpV4Bytes(ipOrMask);
        int address1 = addr[3] & 0xFF;
        address1 |= ((addr[2] << 8) & 0xFF00);
        address1 |= ((addr[1] << 16) & 0xFF0000);
        address1 |= ((addr[0] << 24) & 0xFF000000);
        return address1;
    }

    public static byte[] getIpV4Bytes(String ipOrMask) {
        try {
            String[] addrList = ipOrMask.split("\\.");
            int length = addrList.length;
            byte[] addr = new byte[length];
            for (int index = 0; index < length; index++) {
                addr[index] = (byte) (Integer.parseInt(addrList[index]) & 0xff);
            }
            return addr;
        } catch (Exception ignored) {
        }
        return new byte[4];
    }

    public static void main(String[] args) {
        System.out.println(ipExistsInRange("202.81.231.222", "202.131.48.0-202.131.63.255"));
    }
}