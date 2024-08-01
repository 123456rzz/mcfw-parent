package org.mengchong.mcfw.common.log.service;

import org.mengchong.mcfw.model.entity.system.SysOperLog;

/**
 * 定义保存日志数据的service接口，然后在具体的业务服务中给出实现
 */
public interface AsyncOperLogService {			// 保存日志数据
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}

