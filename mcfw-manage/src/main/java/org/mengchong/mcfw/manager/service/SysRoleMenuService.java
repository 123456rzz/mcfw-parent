package org.mengchong.mcfw.manager.service;

import org.mengchong.mcfw.model.dto.system.AssginMenuDto;

import java.util.Map;

public interface SysRoleMenuService {

    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
