package com.isaiah.eduservice.controller;


import com.isaiah.eduservice.EduTeacherService;
import com.isaiah.eduservice.entity.EduTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    public String testController(){

        String result = "I love you  !";
        return  result;
    }

    @ApiOperation("查询所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }
    /**
     * 逻辑删除讲师
     *
     * @param id
     * @return
     */
    @ApiOperation("逻辑删除教师")
    @DeleteMapping("{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id){
        boolean isDeleted = teacherService.removeById(id);
        return isDeleted;
    }





}

