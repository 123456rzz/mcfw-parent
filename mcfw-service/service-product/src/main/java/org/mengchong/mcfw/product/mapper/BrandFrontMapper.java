package org.mengchong.mcfw.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

@Mapper
public interface BrandFrontMapper {

    //1  查询所有品牌
    List<Brand> findAll();

}