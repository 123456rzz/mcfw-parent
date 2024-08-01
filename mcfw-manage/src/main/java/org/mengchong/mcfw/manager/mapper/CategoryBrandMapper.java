package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.product.CategoryBrandDto;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.entity.product.CategoryBrand;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    // 1 分类品牌条件分页查询
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    //2 分类品牌添加
    void save(CategoryBrand categoryBrand);
    //3  分类品牌修改
    void updateById(CategoryBrand categoryBrand);
    //4  分类品牌删除
    void deleteById(Long id);
    // 5  根据分类ID查询品牌数据
    List<Brand> findBrandByCategoryId(Long categoryId);
}
