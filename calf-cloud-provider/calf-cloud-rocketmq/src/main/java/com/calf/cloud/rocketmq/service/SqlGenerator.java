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

package com.calf.cloud.rocketmq.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlGenerator {


    public static void main(String[] args) {
        //实体类所在的package在磁盘上的绝对路径
        String packageName = "E:/workspaces/self_open/calf-cloud/calf-cloud-provider/calf-cloud-rocketmq/src/main/java/com/calf/cloud/rocketmq"
                + "/pojo/entity";
        //生成sql的文件夹
        String filePath = "E:/create/";
        //项目中实体类的路径
        String prefix = "com.calf.cloud.rocketmq.pojo.entity.";
        String className = "";

        StringBuffer sqls = new StringBuffer();
        //获取包下的所有类名称
        List<String> list = getAllClasses(packageName);
        for (String str : list) {
            className = prefix + str.substring(0, str.lastIndexOf("."));
            String sql = generateSql(className, filePath);
            sqls.append(sql);
        }
        System.out.println(sqls.toString());
        StringToSql(sqls.toString(), filePath + "report.sql");

    }

    /**
     * 根据实体类生成建表语句
     *
     * @param className 全类名
     * @param filePath  磁盘路径  如 : d:/workspace/
     * @author
     * @date 2018年4月11日
     */
    public static String generateSql(String className, String filePath) {
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            className = toUnderlineCase(className);
            Field[] fields = clz.getDeclaredFields();
            StringBuffer column = new StringBuffer();

            String varchar = " varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',";
            for (Field f : fields) {
                column.append(" \n `" + toUnderlineCase(f.getName()) + "`").append(varchar);
            }
            StringBuffer sql = new StringBuffer();
            sql.append("\n DROP TABLE IF EXISTS `" + className + "`; ")
                    .append(" \n CREATE TABLE `" + className + "`  (")
                    .append(" \n `id` bigint unsigned NOT NULL COMMENT '主键',")
                    .append(" \n " + column)
                    .append("\n `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '状态',\n"
                            + "  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',\n"
                            + "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n"
                            + "  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n"
                            + "  `create_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人',\n"
                            + "  `update_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新人',")

                    .append(" \n PRIMARY KEY (`id`) USING BTREE,")
                    .append("\n INDEX `id`(`id`) USING BTREE")
                    .append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;");
            return sql.toString();
        } catch (ClassNotFoundException e) {
            log.debug("该类未找到！");
            return null;
        }

    }

    /**
     * 获取包下的所有类名称,获取的结果类似于 XXX.java
     *
     * @param packageName
     * @return
     * @author
     * @date 2018年4月11日
     */
    public static List<String> getAllClasses(String packageName) {
        List<String> classList = new ArrayList<String>();
        String className = "";
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        } else {
            log.debug("包路径未找到！");
            return null;
        }
    }

    /**
     * 将string 写入sql文件
     *
     * @param str
     * @param path
     * @author
     * @date 2018年4月11日
     */
    public static void StringToSql(String str, String path) {
        byte[] sourceByte = str.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(path);     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close();  //关闭文件输出流
                System.out.println("生成成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String toUnderlineCase(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        camelCaseStr = camelCaseStr.replace("Entity", "");
        // 将驼峰字符串转换成数组
        char[] charArray = camelCaseStr.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
