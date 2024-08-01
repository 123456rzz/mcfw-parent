package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.ProductDetails;

/**
 * @author ljl
 * @create 2023-10-29-17:17
 */

@Mapper
public interface ProductDetailsMapper {
    //1 新增商品sku属性
    public abstract void save(ProductDetails productDetails);

    ProductDetails selectByProductId(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
