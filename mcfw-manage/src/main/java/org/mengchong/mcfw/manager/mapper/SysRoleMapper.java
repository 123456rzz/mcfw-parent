package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.system.SysRoleDto;
import org.mengchong.mcfw.model.entity.system.SysRole;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    //1 角色列表的方法
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    //2 角色添加的方法
    void save(SysRole sysRole);
    // 3 角色修改的方法
    void update(SysRole sysRole);

    //4 角色删除方法
    void delete(Long roleId);
    // 5 查询所有角色
    List<SysRole> findAllRoles();
}
