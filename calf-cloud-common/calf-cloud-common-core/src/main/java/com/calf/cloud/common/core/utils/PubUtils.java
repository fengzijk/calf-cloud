/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月06日 02时04分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-06 02:04:01    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class PubUtils {

    /**
     * The constants STRING_NULL.
     */
    private final static String STRING_NULL = "-";
    /**
     * 匹配手机号码, 支持+86和86开头
     */
    private static final String REGX_MOBILENUM = "^((\\+86)|(86))?(13|15|17|18)\\d{9}$";

    /**
     * 匹配邮箱帐号
     */
    private static final String REGX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";


    public static String[] toStrArray(List<String> list) {
        String[] arr = new String[list.size()];

        return list.toArray(arr);
    }

    /**
     * list去重复(深) 并根据字段进行排序
     */
    public static <T> ArrayList<T> deduplicate(List<T> list, String filedName) {
        Set<T> set = new TreeSet<>((o1, o2) -> {
            String object1 = getValueByFiledName(o1, filedName);
            String object2 = getValueByFiledName(o2, filedName);
            return object1.compareTo(object2);
        });
        set.addAll(list);
        return new ArrayList<>(set);
    }

    /**
     * 根据类的属性名获取值
     *
     * @param object 目标类
     * @param name 目标字段
     * @return 返回该类中该字段的value
     */
    public static String getValueByFiledName(Object object, String name) {
        String value = "";
        try {
            Class objectClass = object.getClass();
            Field field = objectClass.getDeclaredField(name);
            field.setAccessible(true);
            value = String.valueOf(field.get(object));
        } catch (Exception exc) {
            value = "";
        }
        return value;
    }


    public static void main(String[] args) {
        System.out.println(new Random().nextInt(88 - 66 + 1) + 66);
    }


}
