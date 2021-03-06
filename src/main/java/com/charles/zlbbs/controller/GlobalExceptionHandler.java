package com.charles.zlbbs.controller;

import cn.dev33.satoken.exception.NotLoginException;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.domain.enu.ResultCode;
import com.charles.zlbbs.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    private R exceptionHandler(Exception e) {
        log.error("未知异常！原因是：{}", e.getMessage());
        return R.fail(ResultCode.UNKNOWN_ERROR);
    }

    @ResponseBody
    @ExceptionHandler({
                    HttpMediaTypeNotSupportedException.class,
                    HttpMessageNotReadableException.class,
            })
    private R paramExceptionHandler(Exception e,HttpServletRequest request) {
        if (e instanceof HttpMediaTypeNotSupportedException) {
            return R.fail(ResultCode.PARAM_TYPE_ERROR);
        } else if (e instanceof HttpMessageNotReadableException) {
            return R.fail(ResultCode.PARAM_NOT_COMPLETE);
        } else {
            return R.fail(ResultCode.PARAM_NOT_VALID);
        }
    }

    @ResponseBody
    @ExceptionHandler(BizException.class)
    private R bixExceptionHandler(BizException e) {
        log.error("发送业务异常！原因是：{}", e.getErrorMsg());
        return R.fail(e.getErrorCode(), e.getErrorMsg());
    }

    @ResponseBody
    @ExceptionHandler({BindException.class})
    public R handleBindException(BindException e) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        // 生成返回结果
        log.error("发送参数验证异常！原因是：{}", fieldError.getDefaultMessage());
        return R.fail(fieldError.getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public R handleConstraintViolationException(ConstraintViolationException e) {
        // 生成返回结果
        log.error("发送参数验证异常！原因是：{}", e.getMessage().split(": ")[1]);
        return R.fail(e.getMessage().split(": ")[1]);
    }

    @ResponseBody
    @ExceptionHandler(MailSendException.class)
    public R handleMailSendException(MailSendException e) {
        log.error("发送邮件异常！原因是：{}", e.getMessage());
        return R.fail(ResultCode.EMAIL_SEND_ERROR);
    }

    // 全局异常拦截（拦截项目中的NotLoginException异常）
//    @ExceptionHandler(NotLoginException.class)
//    @ResponseBody
//    public R RestHandlerNotLoginException(NotLoginException nle) throws Exception {
//        // 打印堆栈，以供调试
//        nle.printStackTrace();
//
//        // 判断场景值，定制化异常信息
//        String message = "";
//        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
//            message = "未提供token";
//        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
//            message = "token无效";
//        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
//            message = "token已过期";
//        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
//            message = "token已被顶下线";
//        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
//            message = "token已被踢下线";
//        } else {
//            message = "当前会话未登录";
//        }
//
//        // 返回给前端
//        return R.fail(message);
//    }


    @ExceptionHandler(NotLoginException.class)
    public void handlerNotLoginException(NotLoginException nle,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        // 打印堆栈，以供调试
        // nle.printStackTrace();
        log.error("访问{}无权限！原因是：{}", request.getRequestURI(), nle.getMessage());
        response.sendRedirect("/login.html");
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handlerRequestMethodException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("{}不支持{}方法", requestURI, method);
        return R.fail(requestURI + "不支持" + method + "方法");
    }
}
