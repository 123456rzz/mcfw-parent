package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.product.ProductDto;
import org.mengchong.mcfw.model.entity.product.Product;

import java.util.List;

/**
 * @author ljl
 * @create 2023-10-29-16:03
 */
@Mapper
public interface ProductMapper {
    //1 商品条件分页查询
    public abstract List<Product> findByPage(ProductDto productDto);
    //2  商品新增
    public abstract void save(Product product);
    //3 商品查询// 根据id查询商品数据
    public abstract Product selectById(Long id);
    //4 商品修改
    void updateById(Product product);
    //5 商品删除
    void deleteById(Long id);
}
