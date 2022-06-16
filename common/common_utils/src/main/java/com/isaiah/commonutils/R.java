package com.isaiah.commonutils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 同一返回实体
 *
 * @Author: IsaiahLu
 * @date: 2022/6/14 22:12
 */
@Data
public class R {

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("返回吗码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String,Object> data =new HashMap<>();

    /**
     *无参构造方法私有化
     */
    private R(){}


    /**
     * 成功静态方法
     * @return r
     */
    private static R  Success(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * 成功 带参数
     * @return
     */
    public static R Sucess(String code){

        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        System.out.println("hotfix提交");
        System.out.println("hotfix test");
        System.out.println("push test");

        return r;
    }


}
