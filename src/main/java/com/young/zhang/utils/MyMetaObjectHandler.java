package com.young.zhang.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //insert
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Start insert fill ...");
        this.setFieldValByName("create_time", new Date(), metaObject);
        this.setFieldValByName("update_time", new Date(), metaObject);
    }

    //update
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Start update fill ...");
        this.setFieldValByName("update_time", new Date(), metaObject);
    }
}
