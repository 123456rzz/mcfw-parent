package org.mengchong.mcfw.manager.service.impl;


import org.mengchong.mcfw.common.log.service.AsyncOperLogService;
import org.mengchong.mcfw.manager.mapper.SysOperLogMapper;
import org.mengchong.mcfw.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Async      // 异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }


}
