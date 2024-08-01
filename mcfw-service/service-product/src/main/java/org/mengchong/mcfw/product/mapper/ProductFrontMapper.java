package org.mengchong.mcfw.product.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.Product;

/**
 * @author ljl
 * @create 2023-11-02-20:40
 */
@Mapper
public interface ProductFrontMapper {

    // 根据商品id获取Product
    Product getById(Long productId);
}
