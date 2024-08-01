package org.mengchong.mcfw.manager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.manager.service.SysMenuService;
import org.mengchong.mcfw.model.entity.system.SysMenu;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "菜单管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {
   @Autowired
    private SysMenuService sysMenuService;

    /**
     *  //1 菜单列表
     * @return
     */
    @GetMapping("/findNodes")
    public Result findNodes() {
        //调用services的方法进行查询，返回list集合
        List<SysMenu> list = sysMenuService.findNodes();

        return Result.build(list, ResultCodeEnum.SUCCESS);
    }



    /**
     * // 2 菜单添加
     * @param sysMenu 菜单表单数据
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    /**
     * // 3 菜单修改
     * @param sysMenu 菜单表单数据
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 4 菜单删除的方法
     * @param id
     * @return
     */
    @DeleteMapping ("/deleteById/{id}")
    public Result DeleteById(@PathVariable("id") Long id){
        sysMenuService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
