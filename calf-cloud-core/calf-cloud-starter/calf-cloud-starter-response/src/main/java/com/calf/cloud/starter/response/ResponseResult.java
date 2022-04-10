/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月04日 00时59分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-04 00:59:26    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * -------------------------------------------------
 * <pre>统一返回结果</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/4 1:01
 * --------------------------------------------------
 */

@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;
    private Long timestamp = System.currentTimeMillis();
    private boolean success;

    public boolean isSuccess() {
        this.success = this.code == 200;
        return success;
    }


    /**
     * 返回成功数据
     *
     * @param data 数据
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:08
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>()
          .setCode(ResponseStatusEnum.OK.getCode())
          .setMsg(ResponseStatusEnum.OK.getMsg())
          .setData(data);
    }

    /**
     * 返回失败数据
     *
     * @param data 数据
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:11
     */
    public static <T> ResponseResult<T> fail(T data, ResponseStatusEnum responseStatusEnum) {
        return new ResponseResult<T>()
          .setCode(responseStatusEnum.getCode())
          .setMsg(responseStatusEnum.getMsg())
          .setData(data);
    }


    /**
     * 返回失败数据
     *
     * @param responseStatusEnum 数据
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:11
     */
    public static <T> ResponseResult<T> fail(ResponseStatusEnum responseStatusEnum) {
        return new ResponseResult<T>()
          .setCode(responseStatusEnum.getCode())
          .setMsg(responseStatusEnum.getMsg());
    }

    /**
     * 返回失败数据
     *
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:11
     */
    public static <T> ResponseResult<T> fail(int code, String msg) {
        return new ResponseResult<T>()
          .setCode(code)
          .setMsg(msg);
    }


    /**
     * 返回失败数据
     *
     * @param data 数据
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:11
     */
    public static <T> ResponseResult<T> fail(T data) {
        return new ResponseResult<T>()
          .setCode(ResponseStatusEnum.BAD_REQUEST.getCode())
          .setMsg(ResponseStatusEnum.BAD_REQUEST.getMsg())
          .setData(data);
    }


    /**
     * 根据Boolean值动态返回true或false
     *
     * @param result 返回结果
     * @return com.calf.cloud.starter.response.ResponseResult<T>
     * @author : fengzijk
     * @date : 2021/10/4 2:13
     */
    public static <T> ResponseResult<T> result(T result) {
        if (result instanceof Boolean && (Boolean) result) {
            return success(result);
        }
        return fail(result);
    }



}