package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.SysRoleMapper;
import org.mengchong.mcfw.manager.mapper.SysRoleUserMapper;
import org.mengchong.mcfw.manager.service.SysRoleService;
import org.mengchong.mcfw.model.dto.system.SysRoleDto;
import org.mengchong.mcfw.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 角色列表的方法
     * @param sysRoleDto 封装前端提交过来的数据
     * @param current 当前页
     * @param limit 每页显示记录数
     * @return
     */
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit){
        //设置分页参数
        PageHelper.startPage(current,limit);
        //根据条件sysRoleDto查询所有满足条件的数据
        List<SysRole> list=sysRoleMapper.findByPage(sysRoleDto);
        //封账pageInfo对象，PageInfo里设置了分页参数，他会根据参数的值从list筛选数据
        PageInfo<SysRole> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 2 角色添加的方法
     * @param sysRole
     */
    @Override
    public void saveSysRole(SysRole sysRole){
        sysRoleMapper.save(sysRole);
    }

    /**
     * 3 角色修改的方法
     * @param sysRole
     */
    @Override
    public void updateSysRole(SysRole sysRole){
        sysRoleMapper.update(sysRole);
    }

    //4 角色删除的方法
    @Override
    public void deleteById(Long roleId){
        sysRoleMapper.delete(roleId);
    }

    //5 查询所有角色
    @Override
    public Map<String,Object> findAllRoles(Long userId) {
        //1 查询所有角色
        List<SysRole> sysRoleList = sysRoleMapper.findAllRoles();
        //2 分配过的角色列表
        //根据userid查询用户分配过角色id列表
        List<Long> roleIds =  sysRoleUserMapper.selectRoleIdsByUserId(userId);
        Map<String,Object> resultMap = new HashMap<>();
        //将获取到的所有角色添加到map集合中
        resultMap.put("allRolesList",sysRoleList);
        //将前端勾选的用户分配的角色添加到map集合中
        resultMap.put("sysUserRoles",roleIds);
        //返回给前端
        return resultMap;
    }


}
