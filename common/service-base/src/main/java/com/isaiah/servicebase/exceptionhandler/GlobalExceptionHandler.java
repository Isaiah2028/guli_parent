package com.isaiah.servicebase.exceptionhandler;

import com.isaiah.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常异常处理类
 *
 * @Author: IsaiahLu
 * @date: 2022/6/18 8:57
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Exception大异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().massage("执行了全局异常");
    }

    /**
     * 特定异常--ArithmeticException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace(); //打印 异常信息
        return Result.error().massage("执行了ArithmeticExceptione异常");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e) {
        e.printStackTrace();
        return Result.error().code(e.getCode()).massage(e.getMessage());
    }


}
