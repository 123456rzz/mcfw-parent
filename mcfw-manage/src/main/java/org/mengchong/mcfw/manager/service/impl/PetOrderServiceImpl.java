package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.PetOrderMapper;
import org.mengchong.mcfw.manager.service.PetOrderService;
import org.mengchong.mcfw.model.dto.order.PetOrderDto;
import org.mengchong.mcfw.model.entity.order.McPetOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetOrderServiceImpl implements PetOrderService {

    @Autowired
    private PetOrderMapper petOrderMapper;

    /**
     *     // 1 宠物服务列表分页查询接口
     * @param page
     * @param limit
     * @param petOrderDto
     * @return
     */
    @Override
    public PageInfo<McPetOrderInfo> findByPage(Integer page, Integer limit, PetOrderDto petOrderDto) {
        //设置分页参数
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<McPetOrderInfo> list=petOrderMapper.findByPage(petOrderDto);
        //通过pageInfo返回每一页需要的数据
        PageInfo<McPetOrderInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * // 2 宠物服务添加
     * @param mcPetOrderInfo
     */
    @Override
    public void save(McPetOrderInfo mcPetOrderInfo) {
        petOrderMapper.save(mcPetOrderInfo);
    }

    /**
     * // 3 宠物服务修改
     * @param mcPetOrderInfo
     */
    @Override
    public void update(McPetOrderInfo mcPetOrderInfo) {
        //调用保存方法将修改的数据存到数据库
        petOrderMapper.update(mcPetOrderInfo);
    }

    /**
     * // 4 宠物服务删除
     * @param orderId
     */
    @Override
    public void deleteById(Long orderId) {
        petOrderMapper.delete(orderId);
    }
}
