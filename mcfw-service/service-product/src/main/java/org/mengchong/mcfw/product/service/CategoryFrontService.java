package org.mengchong.mcfw.product.service;



import org.mengchong.mcfw.model.entity.product.Category;

import java.util.List;

/**
 * 商品一级分类
 * @author ljl
 * @create 2023-11-01-23:41
 */
// 业务接口
public interface CategoryFrontService {

    //所有的一级分类
    List<Category> findOneCategory();
    //   查询所有的三级分类
    List<Category> findCategoryTree();
}