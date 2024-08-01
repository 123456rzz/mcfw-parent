package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.system.AssginMenuDto;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    //查询角色分配过菜单id列表
    List<Long> findSysRoleMenuByRoleId(Long roleId);
    //删除角色分配菜单数据
    void deleteByRoleId(Long roleId);
    //保存分配数据
    void doAssign(AssginMenuDto assginMenuDto);
}
