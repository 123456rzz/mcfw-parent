package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.ProductSpec;

import java.util.List;

@Mapper
public interface ProductSpecMapper {

    //1 商品规格条件分页查询
    List<ProductSpec> findByPage();

    //    2 商品规格添加
    void save(ProductSpec productSpec);

    //    3 商品规格修改
    void updateById(ProductSpec productSpec);

    //   4 商品规格删除
    void deleteById(Long id);

    //   5 商品规格查询
    List<ProductSpec> findAll();

}
