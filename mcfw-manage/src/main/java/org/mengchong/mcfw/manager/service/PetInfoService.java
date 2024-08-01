package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.pet.PetInfoDto;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

public interface PetInfoService {
    //1 宠物列表分页
    PageInfo<PetInfo> findByPage(Integer page, Integer limit, PetInfoDto petInfoDto);
    // 2 宠物信息添加
    void save(PetInfo petInfo);

    // 3 宠物信息修改
    void update(PetInfo petInfo);
    //    4 宠物信息删除
    void deleteById(Long petId);


}
