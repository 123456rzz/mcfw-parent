package org.mengchong.mcfw.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.h5.ProductSkuDto;
import org.mengchong.mcfw.model.entity.product.ProductSku;

import java.util.List;

@Mapper
public interface ProductSkuFrontMapper {

    //  1 畅销商品列表
    List<ProductSku> findProductSkuBySale();

    //2  商品分页查询
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    //3 根据skuId获取ProductSku
    ProductSku getById(Long skuId);

    //4 根据商品id获取ProductSku列表
    List<ProductSku> findByProductId(Long productId);

    //  更新商品销量
    void updateSale(Long skuId, Integer num);
}