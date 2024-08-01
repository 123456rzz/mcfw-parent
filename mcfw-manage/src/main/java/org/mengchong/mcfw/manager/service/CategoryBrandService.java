package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.product.CategoryBrandDto;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {


    /**
     *   // 1 分类品牌条件分页查询
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param categoryBrandDto 前端提交过来的查询条件数据
     * @return 返回每一页需要的数据
     */
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    //2 分类品牌添加
    void save(CategoryBrand categoryBrand);
    //3  分类品牌修改
    void updateById(CategoryBrand categoryBrand);
    //4  分类品牌删除
    void deleteById(Long id);
    // 5  根据分类ID查询品牌数据
    List<Brand> findBrandByCategoryId(Long categoryId);
}
