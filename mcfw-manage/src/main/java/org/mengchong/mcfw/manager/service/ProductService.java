package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.product.ProductDto;
import org.mengchong.mcfw.model.entity.product.Product;

public interface ProductService {
    //1 商品条件分页查询
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);
    //2  商品新增
    void save(Product product);
    //3 商品查询
    Product getById(Long id);
    //4 商品修改
    void updateById(Product product);
    //5 商品删除
    void deleteById(Long id);
    //6 商品审核
    void updateAuditStatus(Long id, Integer auditStatus);
    //7 商品上架下架
    void updateStatus(Long id, Integer status);








}
