package com.isaiah.servicebase.exceptionhandler;

import com.isaiah.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j   //使用到lom-bak日志文件
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
        log.error(e.getMessage());
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


    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e) {
        log.error(e.getMessage()); //错误日志输出到 error文件里
        e.printStackTrace();
        return Result.error().code(e.getCode()).massage(e.getMessage());
    }


}
