package com.isaiah.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常 --
 *
 * @Author: IsaiahLu
 * @date: 2022/6/18 9:43
 */
@Data
@AllArgsConstructor  //全参构造
@NoArgsConstructor  //无参构造
public class GuliException extends RuntimeException{

    private Integer code ; // 状态码
    private String message; // 异常信息




}
