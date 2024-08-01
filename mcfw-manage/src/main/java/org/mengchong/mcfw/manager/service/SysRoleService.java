package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.system.SysRoleDto;
import org.mengchong.mcfw.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    //1 角色列表的方法
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    //2 角色添加的方法
    void saveSysRole(SysRole sysRole);

    // 3 角色修改的方法
    void updateSysRole(SysRole sysRole);

    //4 角色删除的方法
    void deleteById(Long roleId);

    // 5 查询所有角色
    Map<String,Object> findAllRoles(Long userId);
}
