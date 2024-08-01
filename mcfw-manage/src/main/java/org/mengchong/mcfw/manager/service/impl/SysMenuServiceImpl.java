package org.mengchong.mcfw.manager.service.impl;

import org.mengchong.mcfw.common.exception.GuiguException;
import org.mengchong.mcfw.manager.mapper.SysMenuMapper;
import org.mengchong.mcfw.manager.mapper.SysRoleMenuMapper;
import org.mengchong.mcfw.manager.service.SysMenuService;
import org.mengchong.mcfw.manager.utils.MenuHelper;
import org.mengchong.mcfw.model.entity.system.SysMenu;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.system.SysMenuVo;
import org.mengchong.mcfw.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    /**
     * // 1 菜单列表
     * @return 菜单列表集合
     */
    @Override
    public List<SysMenu> findNodes(){
        //1 查询所有菜单，返回list集合
        List<SysMenu> sysMenuList=sysMenuMapper.findAll();
        //如果数据查询为空，返回
        if(CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }
        //2 调用工具类的方法，把返回list集合封装要求数据格式
        List<SysMenu> treeList= MenuHelper.buildTree(sysMenuList);
        return treeList;
    }



    /**
     *  // 2 菜单添加
     * @param sysMenu
     */
    @Override
    public void save(SysMenu sysMenu){
        sysMenuMapper.save(sysMenu);
        //新添加子菜单，把父菜单isHalf半开状态
//        updateSysRoleMenu(sysMenu);
    }
//    private void updateSysRoleMenu(SysMenu sysMenu){
//        //获取当前添加菜单的父菜单
//        SysMenu parentMenu=sysMenuMapper.selectParentMenu(sysMenu.getParentId());
//        //
//        if(parentMenu!=null){
//            //父菜单isHalf半开状态1
//            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
//        }
//        //递归调用，因为菜单可能有多层
//        updateSysRoleMenu(parentMenu);
//    }
    /**
     * // 3 菜单修改的方法
     * @param sysMenu
     */
    @Override
    public void update(SysMenu sysMenu){
        sysMenuMapper.update(sysMenu);
    }

    /**
     * //4 菜单删除的方法
     * @param id
     */

    @Override
    public void deleteById(Long id){
        // 判断当前要删除的菜单是否包含子菜单
        //根据当前菜单id，查询是否包含子菜单
        int count=sysMenuMapper.selectCountById(id);
        //判断，count>0，包含子菜单，不能删除
        if(count>0){
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }
       // 果不包含子菜单，可以删除 count等于0
        sysMenuMapper.delete(id);
    }

//    /**
//     * 5 查询用户可以操作菜单
//     * @return
//     */
//    @Override
//    public List<SysMenuVo> findMenusByUserId() {
//        //获取当前用户id
//        SysUser sysUser = AuthContextUtil.get();
//        Long userId = sysUser.getId();
//        //根据userId查询可以操作菜单
//        List<SysMenu> syMenuList = sysMenuMapper.findMenusByUserId(userId);
//        //封装要求数据格式，返回
//        List<SysMenu> sysMenuList = MenuHelper.buildTree(syMenuList);
//        List<SysMenuVo> sysMenuVos=this.buildMenus(sysMenuList);
//        return sysMenuVos;
//    }
//
//    // 将List<sysMenu>对象转换成List<sysMenuvo>对象
//    private List<SysMenuVo> buildMenus(List<SysMenu> menus){
//        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
//        for (SysMenu sysMenu : menus) {
//            SysMenuVo sysMenuVo = new SysMenuVo();
//            sysMenuVo.setTitle(sysMenu.getTitle());
//            sysMenuVo.setName(sysMenu.getComponent());
//            List<SysMenu> children = sysMenu.getChildren();
//            if (!CollectionUtils.isEmpty(children)) {
//                sysMenuVo.setChildren(buildMenus(children));
//            }
//            sysMenuVoList.add(sysMenuVo);
//        }
//
//        return sysMenuVoList;
//    }



}
