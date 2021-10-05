/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月04日 00时27分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-04 00:27:27    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.starter.response;

import com.calf.cloud.starter.response.ResponseResult.ResponseStatusEnum;
import com.calf.cloud.starter.response.annotation.IgnoreGlobalResponse;
import com.calf.cloud.starter.response.exception.BusinessException;
import com.calf.cloud.starter.response.json.JsonUtil;
import com.calf.cloud.starter.response.properties.GlobalResponseProperties;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * -------------------------------------------------
 * <pre>全局异常处理</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 19:15
 * --------------------------------------------------
 */
@SuppressWarnings("NullableProblems")
@RestControllerAdvice
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {


    @Autowired
    private GlobalResponseProperties globalResponseProperties;


    /**
     * 拦截MethodArgumentNotValidException异常，针对body参数的表单注解（如：@NotEmpty）校验拦截
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:21
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));

        // 实体参数注解 ，只返回第一个提示
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors) && objectErrors.size() > 0) {
            return ResponseResult.fail(objectErrors.get(0).getDefaultMessage());
        }

        return ResponseResult.fail(null);
    }


    /**
     * 拦截BindException异常，针对form参数的表单注解（如：@NotEmpty）校验拦截
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:22
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseResult<?> bindExceptionHandler(BindException e) {
        log.warn("BindException,message={},Exception={}", e.getMessage(),
          ExceptionUtils.getStackTrace(e));

        // 实体参数注解校验提示格式 只返回第一个提示
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors) && objectErrors.size() > 0) {
            ResponseResult.fail(objectErrors.get(0).getDefaultMessage());
        }

        return ResponseResult.fail(null);
    }


    /**
     * 404异常处理 拦截NoHandlerFoundException异常，针对form参数的表单注解（如：@NotEmpty）校验拦截
     *
     * @param e 错误信息
     * @return java.lang.Object
     * @author : fengzijk
     * @date : 2021/10/4 4:04
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object notFountHandler(NoHandlerFoundException e) {
        log.warn("NoHandlerFoundException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        return ResponseResult.fail(e.getMessage(), ResponseStatusEnum.NO_HANDLER);
    }

    /**
     * 拦截ValidationException异常
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:22
     */
    @ExceptionHandler(value = ValidationException.class)
    public ResponseResult<?> validationExceptionHandler(ValidationException e) {
        log.warn("ValidationException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        return ResponseResult.fail(e.getMessage());
    }


    /**
     * HTTP方法异常调用
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:20
     */
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
    public ResponseResult<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.warn("方法调用方式异常,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        return ResponseResult.fail("方法调用方式异常，Get、Post请求不匹配，或Form、Body参数不匹配");
    }


    /**
     * 拦截MissingServletRequestParameterException异常
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:23
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.warn("MissingServletRequestParameterException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        return ResponseResult.fail("缺少参数");

    }


    /**
     * 拦截自定义的BusinessException异常
     *
     * @param e 错误信息
     * @return com.calf.cloud.starter.response.ResponseResult<?>
     * @author : fengzijk
     * @date : 2021/10/4 2:20
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult<?> daoExceptionHandler(BusinessException e) {
        log.error("DaoException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        return ResponseResult.fail(e.getMessage());

    }


    @SuppressWarnings("NullableProblems")
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return this.filter(methodParameter);
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //o is null -> return response
        if (obj == null) {
            return JsonUtil.tojson(ResponseResult.success(null));
        }
        //当 obj 返回类型为ResultMsg(统一封装返回对象),则直接返回
        if (obj instanceof ResponseResult) {
            return obj;
        }

        return JsonUtil.tojson(ResponseResult.success(obj));
    }


    /**
     * 自定义规则拦截器
     * 过滤: 1.检查过滤包路径
     * 2.检查类过滤列表
     * 3.检查忽略注解是否存在于类上
     * 4.检查注解是否存在于方法上
     *
     * @param methodParameter 方法参数
     * @return java.lang.Boolean
     * @author : fengzijk
     * @date : 2021/10/4 0:30
     */
    private Boolean filter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        //检查过滤包路径
        long count = globalResponseProperties.getAdviceFilterPackage().stream().filter(a -> declaringClass.getName().contains(a)).count();
        if (count > 0) {
            return false;
        }
        //检查<类>过滤列表
        if (globalResponseProperties.getAdviceFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        //检查忽略注解是否存在于类上
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreGlobalResponse.class)) {
            return false;
        }
        //检查注解是否存在于方法上
        return !Objects.nonNull(methodParameter.getMethod()) || !methodParameter.getMethod().isAnnotationPresent(IgnoreGlobalResponse.class);
    }

}
