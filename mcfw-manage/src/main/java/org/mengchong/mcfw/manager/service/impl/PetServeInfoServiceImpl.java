package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.PetServeInfoMapper;
import org.mengchong.mcfw.manager.service.PetServeInfoService;
import org.mengchong.mcfw.model.dto.service.PetServeDto;
import org.mengchong.mcfw.model.entity.service.PetServeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServeInfoServiceImpl implements PetServeInfoService {




    @Autowired
    private PetServeInfoMapper petServeInfoMapper;

    /**
     *     // 1 宠物服务列表分页查询接口
     * @param page
     * @param limit
     * @param petServeDto
     * @return
     */
    @Override
    public PageInfo<PetServeInfo> findByPage(Integer page, Integer limit, PetServeDto petServeDto) {
        //设置分页参数
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<PetServeInfo> list=petServeInfoMapper.findByPage(petServeDto);
        //通过pageInfo返回每一页需要的数据
        PageInfo<PetServeInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * // 2 宠物服务添加
     * @param petServeInfo
     */
    @Override
    public void save(PetServeInfo petServeInfo) {
        petServeInfoMapper.save(petServeInfo);
    }

    /**
     * // 3 宠物服务修改
     * @param petServeInfo
     */
    @Override
    public void update(PetServeInfo petServeInfo) {
        //调用保存方法将修改的数据存到数据库
        petServeInfoMapper.update(petServeInfo);
    }

    /**
     * // 4 宠物服务删除
     * @param serveId
     */
    @Override
    public void deleteById(Long serveId) {
        petServeInfoMapper.delete(serveId);
    }
}
