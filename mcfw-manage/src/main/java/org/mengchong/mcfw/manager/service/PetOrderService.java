package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.order.PetOrderDto;
import org.mengchong.mcfw.model.entity.order.McPetOrderInfo;

public interface PetOrderService {
    //1 宠物列表分页
    PageInfo<McPetOrderInfo> findByPage(Integer page, Integer limit, PetOrderDto petOrderDto);
    // 2 宠物信息添加
    void save(McPetOrderInfo mcPetOrderInfo);

    // 3 宠物信息修改
    void update(McPetOrderInfo mcPetOrderInfo);
    //    4 宠物信息删除
    void deleteById(Long orderId);
}
