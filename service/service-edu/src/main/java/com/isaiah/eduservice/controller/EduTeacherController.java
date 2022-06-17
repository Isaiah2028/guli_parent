package com.isaiah.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.hash.Hashing;
import com.isaiah.commonutils.Result;
import com.isaiah.eduservice.EduTeacherService;
import com.isaiah.eduservice.entity.EduTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author IsaiahLu
 * @since 2022-06-13
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;


    @ApiOperation("测试字符串")
    @GetMapping("/test")
    public String testController() {

        String result = "I love you  !";
        return result;
    }

    @ApiOperation("查询所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Result.success()
                .data("items", list);
    }

    /**
     * 逻辑删除讲师
     *
     * @param id
     * @return
     */
    @ApiOperation("逻辑删除教师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean isDeleted = teacherService.removeById(id);
        if (isDeleted) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 分页查询教师的方法
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,  //当前页
                                  @PathVariable  long limit){   //每页记录数
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);

        teacherService.page(eduTeacherPage, null);

        long total = eduTeacherPage.getTotal(); //总记录数
        List<EduTeacher> records = eduTeacherPage.getRecords(); //数据list集合

        //map组装数据
        Map map = new HashMap();
        map.put("total",total);
        map.put("records",records);
        return Result.success().data(map);
    }


}

