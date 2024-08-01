package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.manager.service.SysRoleService;
import org.mengchong.mcfw.model.dto.system.SysRoleDto;
import org.mengchong.mcfw.model.entity.system.SysRole;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "用户角色接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    //注入services对象
    @Autowired
    private SysRoleService sysRoleService;

    /**
     *  4 角色删除的方法
     * @param roleId
     * @return
     */
    @DeleteMapping ("/deleteById/{roleId}")
    public Result DeleteById(@PathVariable("roleId") Long roleId){
        sysRoleService.deleteById(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  3 角色修改的方法
     * @param sysRole
     * @return
     */
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  2 角色添加的方法
     * @param sysRole
     * @return
     */
    @Log(title = "角色添加",businessType = 0) //在要添加日志功能的业务接口方法上添加**Log**注解，设置属性
    @PostMapping(value="/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 1 角色列表的方法
     * @param current 当前页
     * @param limit 每页显示记录数
     * @param sysRoleDto 使用@Requestbody封装前端提交过来的数据
     * @return
     */
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<SysRole> pageInfo= sysRoleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    /**
     * 5 查询所有角色
     * @return
     */
    @GetMapping(value="/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId){
        //返回map集合，一个存放已选择的角色，再点击分配角色时默认勾选
        Map<String,Object> resultMap = sysRoleService.findAllRoles(userId);
        //
        return Result.build(resultMap,ResultCodeEnum.SUCCESS);
    }

}
