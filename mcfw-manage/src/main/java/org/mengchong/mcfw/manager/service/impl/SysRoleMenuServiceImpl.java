package org.mengchong.mcfw.manager.service.impl;

import org.mengchong.mcfw.manager.mapper.SysRoleMenuMapper;
import org.mengchong.mcfw.manager.service.SysMenuService;
import org.mengchong.mcfw.manager.service.SysRoleMenuService;
import org.mengchong.mcfw.manager.utils.MenuHelper;
import org.mengchong.mcfw.model.dto.system.AssginMenuDto;
import org.mengchong.mcfw.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 1 查询所有菜单 和 查询角色分配过的菜单id
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //1 查询所有菜单，返回list集合
        List<SysMenu> sysMenuList=sysMenuService.findNodes();
        //2 查询角色分配过菜单id列表
        List<Long> roleMenuIds=sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        //3  将上面查询所有菜单，角色分配过菜单id列表，存放到map集合
        Map<String, Object> map=new HashMap<>();
        map.put("sysMenuList",sysMenuList);//所有菜单集合
        map.put("roleMenuIds",roleMenuIds);//角色分配过菜单id列表
        return map;
    }

    /**
     * 2 保存角色分配菜单数据
     * @param assginMenuDto
     */
    @Override
    public void doAssign(AssginMenuDto assginMenuDto){
        //删除角色分配菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //保存分配数据
        List<Map<String,Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size()>0){ //角色分配了菜单
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }



}
