package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.PetInfoMapper;
import org.mengchong.mcfw.manager.service.PetInfoService;
import org.mengchong.mcfw.model.dto.pet.PetInfoDto;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetInfoServiceImpl implements PetInfoService {
    @Autowired
    private PetInfoMapper petInfoMapper;


    /**
     * // 1  宠物服务列表分页查询
     * @param page 当前页
     * @param limit 每页记录数
     * @param petInfoDto  查询条件对象
     * @return  返回每一页需要的数据
     */
    @Override
    public PageInfo<PetInfo> findByPage(Integer page, Integer limit, PetInfoDto petInfoDto) {
        //设置分页参数
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<PetInfo> list=petInfoMapper.findByPage(petInfoDto);
        //通过pageInfo返回每一页需要的数据
        PageInfo<PetInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * // 2  宠物服务添加
     * @param petInfo
     */
    @Override
    public void save(PetInfo petInfo) {
        //调用工具类，获取当前登录用户信息
        SysUser sysUser= AuthContextUtil.get();
        //获取宠物主人id
        Long userId=sysUser.getId();
        //获取创建人
        String username=sysUser.getUserName();
        //把当前登录用户id赋给宠物主人id
        petInfo.setPetOwnerId(userId);
        petInfo.setCreateUser(username);
        petInfoMapper.save(petInfo);
    }

    /**
     * // 3  宠物服务修改
     * @param petInfo
     */
    @Override
    public void update(PetInfo petInfo) {
        //调用保存方法将修改的数据存到数据库
        petInfoMapper.update(petInfo);
    }

    /**
     * // 4 宠物服务删除
     * @param petId
     */
    @Override
    public void deleteById(Long petId) {
        petInfoMapper.delete(petId);
    }


}
