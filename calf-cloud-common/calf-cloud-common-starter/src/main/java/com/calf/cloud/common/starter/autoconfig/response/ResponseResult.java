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

package com.calf.cloud.common.starter.autoconfig.response;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * -------------------------------------------------
 * <pre>统一返回结果</pre>
 *
 * @author : guozhifeng
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
     * @return com.calf.cloud.common.starter.autoconfig.response.ResponseResult<T>
     * @author : guozhifeng
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
     * @return com.calf.cloud.common.starter.autoconfig.response.ResponseResult<T>
     * @author : guozhifeng
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
     * @param data 数据
     * @return com.calf.cloud.common.starter.autoconfig.response.ResponseResult<T>
     * @author : guozhifeng
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
     * @return com.calf.cloud.common.starter.autoconfig.response.ResponseResult<T>
     * @author : guozhifeng
     * @date : 2021/10/4 2:13
     */
    public static <T> ResponseResult<T> result(T result) {
        if (result instanceof Boolean && (Boolean) result) {
            return success(result);
        }
        return fail(result);
    }


    @Getter
    public enum ResponseStatusEnum {

        /**
         * 请求成功
         */
        OK(200, "请求成功"),

        /**
         * 请求失败
         */
        BAD_REQUEST(400, "请求失败"),

        /**
         * 请求失败
         */
        NO_HANDLER(404, "资源不存在"),
        /**
         * 系统内部错误
         */
        SYSTEM_ERROR(500, "系统内部错误");


        private Integer code;
        private String msg;

        ResponseStatusEnum(Integer code, String name) {
            this.code = code;
            this.msg = name;
        }


        public static String getMsgByCode(Integer code) {
            if (code != null) {
                for (ResponseStatusEnum e : values()) {
                    if (e.getCode().equals(code)) {
                        return e.getMsg();
                    }
                }
            }
            return null;
        }

        public static ResponseStatusEnum getByCode(Integer code) {
            if (code != null) {
                for (ResponseStatusEnum e : values()) {
                    if (e.getCode().equals(code)) {
                        return e;
                    }
                }
            }
            return null;
        }
    }


}