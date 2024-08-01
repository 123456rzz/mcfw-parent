package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.system.SysOperLog;

@Mapper
public interface SysOperLogMapper {

    public abstract void insert(SysOperLog sysOperLog);
}
