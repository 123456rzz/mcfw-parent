package org.mengchong.mcfw.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liurui
 * @description: 填充数据对象属性
 * @date 2023/6/13 13:50
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("createTime")) {
            metaObject.setValue("createTime", DateUtils.asDate(LocalDateTime.now()));
        }
        if (metaObject.hasGetter("updateTime")) {
            metaObject.setValue("updateTime", DateUtils.asDate(LocalDateTime.now()));
        }
        if (metaObject.hasGetter("createUserId")) {
            metaObject.setValue("createUserId", AuthContextUtil.get().getId());
        }
        if (metaObject.hasGetter("createPetId")) {
            metaObject.setValue("createPetId", AuthContextUtil.get().getPetId());
        }
        if (metaObject.hasGetter("createUserName")) {
            metaObject.setValue("createUserName", AuthContextUtil.get().getUserName());
        }
        if (metaObject.hasGetter("isDeleted")) {
            metaObject.setValue("isDeleted", AuthContextUtil.get().getIsDeleted());
        }
        if (metaObject.hasGetter("status")) {
            metaObject.setValue("status", AuthContextUtil.get().getStatus());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updateTime")) {
            metaObject.setValue("updateTime", DateUtils.asDate(LocalDateTime.now()));
        }
    }
}
