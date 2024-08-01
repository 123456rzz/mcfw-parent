package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.manager.service.SysUserService;
import org.mengchong.mcfw.model.dto.system.AssginRoleDto;
import org.mengchong.mcfw.model.dto.system.SysUserDto;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;



    /**
     *  1 用户条件分页查询接口
     * @param pageNum 当前页
     * @param pageSize 每页显示记录数
     * @param sysUserDto 使用@Requestbody封装前端提交过来的数据
     * @return
     */
    @GetMapping(value ="/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize,
                              SysUserDto sysUserDto) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize, sysUserDto);
        //
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }



    /**
     *  //    2 用户添加
     * @param sysUser
     * @return
     */
    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser){
        sysUserService.saveSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    /**
     *  //     3 用户修改
     * @param sysUser
     * @return
     */
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }



    /**
     *  //    4 用户删除
     * @param userId 用户id
     * @return
     */
    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable("userId") Long userId){
        sysUserService.deleteById(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 5 用户分配角色(一对多)
     * @param assignRoleDto 分配角色
     * @return
     */
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assignRoleDto){

        sysUserService.doAssign(assignRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
