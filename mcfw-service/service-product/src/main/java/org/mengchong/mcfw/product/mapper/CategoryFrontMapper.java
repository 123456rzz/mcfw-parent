package org.mengchong.mcfw.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.Category;

import java.util.List;

@Mapper
public interface CategoryFrontMapper {


    //所有的一级分类
    List<Category> findOneCategory();

    //2 查询所有商品
    List<Category> findAll();
}