package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.order.PetOrderDto;
import org.mengchong.mcfw.model.entity.order.McPetOrderInfo;

import java.util.List;

@Mapper
public interface PetOrderMapper {

    // 1 宠物服务列表分页查询接口
    List<McPetOrderInfo> findByPage(PetOrderDto petOrderDto);
    // 2 宠物服务添加
    void save(McPetOrderInfo mcPetOrderInfo);
    // 3 宠物服务修改
    void update(McPetOrderInfo mcPetOrderInfo);
    // 4 宠物服务删除
    void delete(Long orderId);
}
