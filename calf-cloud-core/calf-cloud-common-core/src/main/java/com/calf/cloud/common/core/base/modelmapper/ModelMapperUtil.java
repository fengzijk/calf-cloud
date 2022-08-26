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
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>bean拷贝</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 1:54
 */
@Component
public class ModelMapperUtil {
    private static final ModelMapper modelMapper = SingletonModelMapper.getInstance();


    public static <S, T> T map(final S source, Class<T> target) {
        if (source == null) {
            return null;
        }
        return mapClass(source, target, null);

    }


    /**
     * 实体转换
     *
     * @param source 原始
     * @param target 目标
     * @return 目标类型的实体
     */
    public static <S, T> T mapClass(final S source, Class<T> target, ConvertCallBack<S, T> callBack) {
        if (source == null) {
            return null;
        }
        T t = modelMapper.map(source, target);
        if (callBack != null) {
            callBack.callBack(source, t);
        }
        return t;
    }

    /**
     * 集合转换
     *
     * @param source 原始集合
     * @param <T>
     * @return 目标类型的集合
     */
    public static <S, T> List<T> mapList(final Collection<S> source, Class<T> target, ConvertCallBack<S, T> callBack) {
        if (source == null) {
            return null;
        }
        return source.stream()
                .map(entity -> mapClass(entity, target, callBack))
                .collect(Collectors.toList());
    }


    public static <S, T> T map(final S source, T target, ConvertCallBack<S, T> callBack) {
        modelMapper.map(source, target);
        if (callBack != null) {
            callBack.callBack(source, target);
        }
        return target;
    }

    /**
     * 回调接口
     *
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     */
    @FunctionalInterface
    public interface ConvertCallBack<S, T> {
        void callBack(S t, T s);
    }
}
