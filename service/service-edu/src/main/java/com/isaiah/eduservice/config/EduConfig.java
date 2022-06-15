package com.isaiah.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: IsaiahLu
 * @date: 2022/6/13 20:40
 */
@Configuration
@MapperScan("com.isaiah.eduservice.mapper")
public class EduConfig {

    /**
     *逻辑删除插件
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }


}
