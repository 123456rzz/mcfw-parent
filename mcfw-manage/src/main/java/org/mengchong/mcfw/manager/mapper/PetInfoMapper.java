package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.pet.PetInfoDto;
import org.mengchong.mcfw.model.entity.pet.PetInfo;

import java.util.List;

@Mapper
public interface PetInfoMapper {
    // 1 宠物列表分页
    List<PetInfo> findByPage(PetInfoDto petInfoDto);
    // 2 宠物信息添加
    void save(PetInfo petInfo);
    // 3 宠物信息修改
    void update(PetInfo petInfo);
    // 4 宠物信息删除
    void delete(Long petId);
}
