package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.ProductSku;

import java.util.List;

/**
 * @author ljl
 * @create 2023-10-29-17:17
 */

@Mapper
public interface ProductSkuMapper {
    public abstract void save(ProductSku productSku);

    public List<ProductSku> selectByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
