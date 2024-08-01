package org.mengchong.mcfw.manager.service;

import org.mengchong.mcfw.model.entity.system.SysMenu;
import org.mengchong.mcfw.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    //1 菜单列表
    List<SysMenu> findNodes();

    // 2 菜单添加
    void save(SysMenu sysMenu);

    // 3 菜单修改
    void update(SysMenu sysMenu);

    // 4 菜单删除的方法
    void deleteById(Long id);
    //查询用户可以操作菜单,实现动态菜单
//    List<SysMenuVo> findMenusByUserId();
}
