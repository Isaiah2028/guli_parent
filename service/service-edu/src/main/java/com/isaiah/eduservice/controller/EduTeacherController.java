package com.isaiah.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isaiah.commonutils.Result;
import com.isaiah.eduservice.EduTeacherService;
import com.isaiah.eduservice.entity.EduTeacher;
import com.isaiah.eduservice.entity.vo.TeacherQuery;
import com.isaiah.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @ApiOperation("分页查询教师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,  //当前页
                                  @PathVariable long limit) {   //每页记录数
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);


        try {
            int i = 10/0 ;
        } catch (Exception e) {
         throw   new GuliException(20001,"执行了GuliException自定义异常");
        }

        teacherService.page(eduTeacherPage, null);
        long total = eduTeacherPage.getTotal(); //总记录数
        List<EduTeacher> records = eduTeacherPage.getRecords(); //数据list集合

        //map组装数据
        Map map = new HashMap();
        map.put("total", total);
        map.put("records", records);
        return Result.success().data(map);
    }

    /**
     * 条件查询带分页的方法
     */
    @ApiOperation("根据条件查询教师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //动态sql
        //判断条件是否为空，如果不为空拼接条件

        String name = teacherQuery.getName(); //讲师名称
        Integer level = teacherQuery.getLevel();//讲师级别
        String begin = teacherQuery.getBegin();//开始时间
        String end = teacherQuery.getEnd(); //结束时间

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.gt("gmt_create", begin); //gmt_create 是数据库表中的字段
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", name); //gmt_modified 是数据库表中的字段
        }

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return Result.success().data("total", total).data("row", records);
    }

    /**
     * 添加讲师接口
     */

    @ApiOperation("添加讲师接口")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody(required = false) EduTeacher eduTeacher) {


        boolean isSaved = teacherService.save(eduTeacher);
        if (isSaved) { //添加成功
            return Result.success().massage("添加成功");
        } else { //添加失败
            return Result.error().massage("添加失败");
        }
    }

    /**
     * 根据讲师id查询
     */
    @ApiOperation("根据讲师id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacherById(@RequestBody(required = false) String id) {

        EduTeacher teacher = teacherService.getById(id);

        return Result.success().massage("查询成功").data("teacher", teacher);

    }

    /**
     * 讲师修改功能
     */

    @ApiOperation("修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean isAdded = teacherService.updateById(eduTeacher);
        if (isAdded) {
            return Result.success().massage("添加成功");
        } else {
            return Result.error().massage("添加失败");
        }
    }

}

