package com.isaiah.commonutils;

import io.swagger.annotations.ApiModelProperty;
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
public class Result {

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
    public Result(){}


    /**
     * 成功静态方法
     * @return r
     */
    public static Result success(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }


    //失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public Result sucess(Boolean success){
        this.setMessage(message);
        return this ;
    }

    public Result massage(String message){
        this.setMessage(message);
        return this;
    }
    public Result code (Integer code){
        this.setCode(code);
        return this;
    }

    public Result data (String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public Result data (Map<String ,Object> map){
        this.setData(map);
        return this;
    }

}
