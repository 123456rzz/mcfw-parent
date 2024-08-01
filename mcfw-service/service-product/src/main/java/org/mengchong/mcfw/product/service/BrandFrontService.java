package org.mengchong.mcfw.product.service;



import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

public interface BrandFrontService {

    //1  查询所有品牌
    List<Brand> findAll();
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateBrand(Brand brand);

    void deleteById(Long brandId);



}