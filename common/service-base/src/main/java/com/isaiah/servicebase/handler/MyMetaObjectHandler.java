package com.isaiah.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: IsaiahLu
 * @date: 2022/6/17 0:07
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //添加，修改时间
        this.setFieldValByName("gmtCreate",new Date(),metaObject); //添加时间
        this.setFieldValByName("gmtModified",new Date(),metaObject);//修改时间

        //逻辑删除
        this.setFieldValByName("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        this.setFieldValByName("gmtModified",new Date(),metaObject);

    }

}
