/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 19时17分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 19:17:35    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.base.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * -------------------------------------------------
 * <pre>全局异常处理</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 19:15
 * --------------------------------------------------
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseControllerAdvice implements ResponseBodyAdvice<ResponseEntity<?>> {

//    /**
//     * 拦截MethodArgumentNotValidException异常，针对body参数的表单注解（如：@NotEmpty）校验拦截
//     *
//     * @param e 错误信息
//     * @return org.springframework.http.ResponseEntity<?>
//     * @author : guozhifeng
//     * @date : 2021/10/3 19:23
//     */
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        log.warn("MethodArgumentNotValidException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
//
//        // 实体参数注解 ，只返回第一个提示
//        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
//        if (!CollectionUtils.isEmpty(objectErrors) && objectErrors.size() > 0) {
//            return new ResponseEntity<>().badValidated(objectErrors.get(0).getDefaultMessage());
//        }
//
//        return new ResponseEntity<>().badValidated();
//    }
//
//
//    /**
//     * 拦截BindException异常，针对form参数的表单注解（如：@NotEmpty）校验拦截
//     *
//     * @param e 错误信息
//     * @return org.springframework.http.ResponseEntity<?>
//     * @author : guozhifeng
//     * @date : 2021/10/3 19:24
//     */
//    @ExceptionHandler(value = BindException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> bindExceptionHandler(BindException e) {
//        log.warn("BindException,message={},Exception={}", e.getMessage(),
//          ExceptionUtils.getStackTrace(e));
//
//        // 实体参数注解校验提示格式 只返回第一个提示
//        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
//        if (!CollectionUtils.isEmpty(objectErrors) && objectErrors.size() > 0) {
//            return new ResponseEntity<>().badValidated(objectErrors.get(0).getDefaultMessage());
//        }
//
//        return new ResponseEntity<>().badValidated();
//    }
//
//
//    /**
//     * 拦截ValidationException异常
//     *
//     * @param e 错误信息
//     * @return org.springframework.http.ResponseEntity<?>
//     * @author : guozhifeng
//     * @date : 2021/10/3 19:28
//     */
//    @ExceptionHandler(value = ValidationException.class)
//    public ResponseEntity<?> validationExceptionHandler(ValidationException e) {
//        log.warn("ValidationException,message={},Exception={}", e.getMessage(),
//          ExceptionUtils.getStackTrace(e));
//        return new ResponseEntity<>().badValidated(e.getMessage());
//    }
//
//    /**
//     * HTTP方法异常调用
//     *
//     * @param e 错误信息
//     * @return org.springframework.http.ResponseEntity<?>
//     * @author : guozhifeng
//     * @date : 2021/10/3 19:28
//     */
//    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
//    public ResponseEntity<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
//        log.warn("方法调用方式异常,message={},Exception={}", e.getMessage(),
//          ExceptionUtils.getStackTrace(e));
//        return new ResponseEntity<>().systemError("method_not_supported#方法调用方式异常，Get、Post请求不匹配，或Form、Body参数不匹配");
//    }
//
//
//    /**
//     * 拦截MissingServletRequestParameterException异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = MissingServletRequestParameterException.class)
//    public ResponseEntity<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
//        log
//          .warn("MissingServletRequestParameterException,message={},Exception={}", e.getMessage(), ExceptionUtils.getStackTrace(e));
//        return new ResponseEntity<>().badValidated("missing_servlet_request_parameter_exception#缺少参数");
//
//    }
//
//
//    /**
//     * 拦截自定义的BusinessException异常
//     *
//     * @param e 错误信息
//     * @return org.springframework.http.ResponseEntity<?>
//     * @author : guozhifeng
//     * @date : 2021/10/3 19:29
//     */
//    @ExceptionHandler(value = BusinessException.class)
//    public ResponseEntity<?> daoExceptionHandler(BusinessException e) {
//        log.error("DaoException,message={},Exception={}", e.getMessage(),
//          ExceptionUtils.getStackTrace(e));
//        return new ResponseEntity<>().badRequest(e.getMessage());
//
//    }
//
//
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        return false;
//    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public ResponseEntity<?> beforeBodyWrite(ResponseEntity<?> responseEntity, MethodParameter methodParameter, MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        String msg = responseEntity.getStatusMessage();
//        responseEntity.setStatusMessage(lang.getText(msg));
        return null;

    }
}
