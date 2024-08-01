package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {

    //1 根据userId删除用户之前分配角色数据
    public void deleteByUserId(Long userId);

    //2 重新分配数据
    public void doAssign(Long userId, Long roleId);

    List<Long> selectRoleIdsByUserId(Long userId);
}
