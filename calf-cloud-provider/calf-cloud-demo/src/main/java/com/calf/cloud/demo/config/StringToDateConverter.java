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

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class StringToDateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return null;
    }
}

//    @Override
//    public Date convert(String source) {
//        for (DateFormatStyleEnum formatStyle : DateFormatStyleEnum.values()) {
//            return DateUtilsPlus.formatStringByStyle(source.trim(), formatStyle.getDateStyle());
//        }
//        return DateUtilsPlus.formatStringByStyle(source.trim(), DateFormatStyleEnum.CN_DATE_BASIC_STYLE.getDateStyle());
//    }
//}
