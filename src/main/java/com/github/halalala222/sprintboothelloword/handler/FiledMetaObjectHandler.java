package com.github.halalala222.sprintboothelloword.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Component
public class FiledMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createdAt";
    private final static String UPDATE_DATE = "updatedAt";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(CREATE_DATE, new Date(),metaObject);
        this.setFieldValByName(UPDATE_DATE,new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_DATE,new Date(),metaObject);
    }
}
