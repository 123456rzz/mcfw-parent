package org.mengchong.mcfw.manager.utils;

import org.mengchong.mcfw.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装树形菜单数据
 */
public class MenuHelper {
    /**
     * 通过递归实现封装过程
     * @param sysMenuList 所有菜单集合
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //sysMenuList 所有菜单集合，遍历
        //创建list集合，用于封装最终的数据
        List<SysMenu> trees=new ArrayList<>();
        //遍历所有菜单集合
        for(SysMenu sysMenu:sysMenuList){
            //1 找到递归操作入口，菜单的第一层菜单
            //条件 parent_id=0
            if(sysMenu.getParentId().longValue()==0){ //longValue转换为包装类
                //根据第一层，找下层数据，使用递归完成
                //写方法实现找下层过程，方法里面传递两个参数：第一个参数当前第一层菜单，第二个参数是所有菜单集合
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     *
     * @param sysMenu 当前第一层菜单
     * @param sysMenuList 所有菜单集合
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        // SysMenu属性：private List<SysMenu> children; 封装下一层数据
        sysMenu.setChildren(new ArrayList<>());
        //递归查询
        //sysMenu 的id值如果和parentId相同，
        for(SysMenu it:sysMenuList){
            //判断id是否和parentId相同
            if(sysMenu.getId().longValue()==it.getParentId().longValue()){ //第二层
                //it就是下层数据，进行封装
                sysMenu.getChildren().add(findChildren(it,sysMenuList)); //找第三层、第四层
            }
        }
        return sysMenu;

    }

    //新建类，传入所有菜单的list集合

}
