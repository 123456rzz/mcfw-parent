package org.mengchong.mcfw.manager.controller;

import org.mengchong.mcfw.manager.service.SysRoleMenuService;
import org.mengchong.mcfw.model.dto.system.AssginMenuDto;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    /**
     *  //1 查询所有菜单 和 查询角色分配过的菜单id
     * @param roleId 角色id
     * @return  map集合一个存放所有菜单，一个存放角色分配过的菜单id列表
     */
    @GetMapping("/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable("roleId") Long roleId) {
        //调用services层的方法，查询数据返回给map集合，map集合一个存放所有菜单，一个存放角色分配过菜单id列表
        Map<String,Object> map = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
    //2 保存角色分配菜单数据
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build( null, ResultCodeEnum.SUCCESS);
    }


}
