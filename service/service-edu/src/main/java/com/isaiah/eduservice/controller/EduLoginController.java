package com.isaiah.eduservice.controller;

import com.isaiah.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录接口
 *
 * @Author: IsaiahLu
 * @date: 2022/6/20 23:37
 */

@Api("登录接口")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //跨域
public class EduLoginController {

    //login

    /**
     * 登录接口
     * @return
     */

    @ApiOperation("讲师登录接口")
    @PostMapping("login")
    public Result login(){

   return Result.success().data("token","admin");

    }

    //info

    @ApiOperation("讲师信息接口")
    @GetMapping("info")
    public Result info(){
        return Result.success().data("role","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }




}

