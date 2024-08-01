package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.system.SysMenu;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    //1 菜单列表
    List<SysMenu> findAll();

    // 2 菜单添加的方法
    void save(SysMenu sysMenu);

    // // 3 菜单修改的方法
    void update(SysMenu sysMenu);

    //4 菜单删除的方法
    void delete(Long roleId);

    //根据当前菜单id，查询是否包含子菜单
    int selectCountById(Long id);

    //查询用户可以操作菜单
//    List<SysMenu> findMenusByUserId(Long userId);
//    //获取当前添加菜单的父菜单
//    SysMenu selectParentMenu(Long parentId);
}
