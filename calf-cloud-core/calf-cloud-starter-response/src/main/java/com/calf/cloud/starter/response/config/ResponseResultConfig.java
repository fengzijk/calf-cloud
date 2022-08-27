/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年08月28日 01时24分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-08-28 01:24:46    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.util.List;

/**
 * <pre>
 * 如果有String类型的返回值，就有可能遇到类型不匹配的问题。HttpMessageConverter是根据Controller的原始返回值类型进行处理的，
 * 而我们在ResponseAdvisor中改变了返回值的类型。如果HttpMessageConverter处理的目标类型是Object还好
 * 如果是其它类型就会出现问题，其中最容易出现问题的就是String类型，
 * 因为在所有的HttpMessageConverter实例集合中,
 * StringHttpMessageConverter要比其它的Converter排得靠前一些。
 * 我们需要尝试将处理Object类型的HttpMessageConverter放得靠前一些
 * @link <a href="https://www.yzlfxy.com/jiaocheng/java/418622.html">...</a>
 * </pre>
 *
 * @author guozhifeng
 * @date 2022/8/28 1:25
 */
@Configuration
public class ResponseResultConfig extends DelegatingWebMvcConfiguration {
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}
