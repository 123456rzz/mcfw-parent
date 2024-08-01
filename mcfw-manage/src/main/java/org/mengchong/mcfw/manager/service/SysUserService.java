package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.system.AssginRoleDto;
import org.mengchong.mcfw.model.dto.system.LoginDto;
import org.mengchong.mcfw.model.dto.system.SysUserDto;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     * 根据用户名查询用户数据
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto) ;

    //获取当前登录用户信息
    SysUser getUserInfo(String token);

   //用户登出
    void logout(String token);

    //1 用户条件分页查询接口
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);
    //    2 用户添加
    void saveSysUser(SysUser sysUser);
    //     3 用户修改
    void updateSysUser(SysUser sysUser);

    //    4 用户删除
    void deleteById(Long userId);

    //    5 用户分配角色(一对多)
    void doAssign(AssginRoleDto assignRoleDto);
}
