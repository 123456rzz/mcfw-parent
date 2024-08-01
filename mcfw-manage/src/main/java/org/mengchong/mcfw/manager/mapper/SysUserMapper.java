package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.system.SysUserDto;
import org.mengchong.mcfw.model.entity.system.SysUser;

import java.util.List;

@Mapper
public interface SysUserMapper {
    //2 根据用户名查询数据库表 sys_user表
    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    public abstract SysUser selectByUserName(String userName) ;

    //    1 用户条件分页查询接口
    List<SysUser> findByPage(SysUserDto sysUserDto);
    //    2 用户添加
    void save(SysUser sysUser);
    //    3 用户修改
    void update(SysUser sysUser);
    //    4 用户删除
    void delete(Long userId);
}
