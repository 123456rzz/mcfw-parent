package org.mengchong.mcfw.product.service;



import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.entity.product.Brand;

import java.util.List;

public interface PetFrontService {

    //1  查询所有品牌
    List<PetInfo> findAll();

//    PageInfo<PetInfo> findByPage(Integer page, Integer limit);
//
//    void save(PetInfo brand);
//
//    void updateBrand(PetInfo brand);
//
//    void deleteById(Long brandId);



}