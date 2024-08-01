package org.mengchong.mcfw.product.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.ProductDetails;

/**
 * @author ljl
 * @create 2023-11-02-20:37
 */
@Mapper
public interface ProductDetailsFrontMapper {

    // 根据商品id获取ProductDetails
    ProductDetails getByProductId(Long productId);
}
