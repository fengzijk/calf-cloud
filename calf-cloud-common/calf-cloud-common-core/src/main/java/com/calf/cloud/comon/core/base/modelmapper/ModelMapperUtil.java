/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 01时29分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 01:29:04    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.base.modelmapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
/**
*-------------------------------------------------
* <pre>bean拷贝</pre>
* @author : fengzijk
* @date : 2021/10/3 1:54
*--------------------------------------------------
*/
@Component
public class ModelMapperUtil {
    private static ModelMapper modelMapper = SingletonModelMapper.getInstance();

    /**
     * 实体转换
     *
     * @param entity   原始
     * @param outClass 目标
     * @return 目标类型的实体
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, outClass);
    }

    /**
     * 集合转换
     *
     * @param entityList 原始集合
     * @param d   目标集合类
     * @return 目标类型的集合
     */
    public static <D, T> List<D> mapList(final Collection<T> entityList, Class<D> d) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(entity -> map(entity, d))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}