package com.isaiah.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 条件查询讲师前端入参vo实体类
 * @Author: IsaiahLu
 * @date: 2022/6/17 20:15
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔：1高级讲师，2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2022-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2022-12-01 10:10:10")
    private String end;
}
