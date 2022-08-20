package com.isaiah.eduservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isaiah.commonutils.Result;
import com.isaiah.eduservice.service.JingDongSign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: IsaiahLu
 * @date: 2022/8/20 16:26
 */
@Api(
        description = "京东物流接口"
)
@RestController
@RequestMapping({"/v1/high/sign"})
public class JindongController {
    @Autowired
    private JingDongSign jingDongSign;

    public JindongController() {
    }

    @ApiOperation(value = "获取物流信息", notes = "获取物流信息")
    @PostMapping({"/getSign"})
    public Result getSign(@RequestBody JSONArray message) {
        Result result = new Result();
        try {
            JSONObject jsonObject = this.jingDongSign.getSign(message);
            result.setCode(200);
            result.setMessage("获取物流信息成功！");
            result.setData((Map)jsonObject.get("data"));
        } catch (Exception var4) {
            result.setMessage("获取物流信息失败！" + var4.getMessage());
            result.setData((Map)null);
            result.setCode(500);
        }

        return result;
    }
}