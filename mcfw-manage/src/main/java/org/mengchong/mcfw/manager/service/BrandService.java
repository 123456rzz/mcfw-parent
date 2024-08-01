package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    //列表查询
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    //添加
    void save(Brand brand);
    //     3 品牌修改
    void updateBrand(Brand brand);

    //    4 品牌删除
    void deleteById(Long brandId);

    //5 查询所有品牌
    List<Brand> findAll();
}
