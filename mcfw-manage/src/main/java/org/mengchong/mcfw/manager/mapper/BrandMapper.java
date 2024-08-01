package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

@Mapper
public interface BrandMapper {

    //1 列表
    List<Brand> findByPage();

    //2 品牌添加
    void save(Brand brand);
    //    3 品牌修改
    void update(Brand brand);
    //    4 品牌删除
    void delete(Long brandId);

    //查询所有品牌
    List<Brand> findAll();
}
