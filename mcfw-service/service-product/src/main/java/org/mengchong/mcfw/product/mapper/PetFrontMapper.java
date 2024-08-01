package org.mengchong.mcfw.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.pet.PetInfo;

import java.util.List;

@Mapper
public interface PetFrontMapper {

    //1  查询所有品牌
    List<PetInfo> findAll();

}