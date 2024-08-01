package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.model.vo.product.CategoryExcelVo;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //1 根据id条件值进行查询，返回List集合
    List<Category> selectCategoryByParentId(Long id);

    //2 判断每个分类是否有下一层分类
    int selectCountByParentId(Long id);

    //3 导出 调用mapper方法查询所有分类，返回List集合
    List<Category> findAll();

    void batchInsert(List<CategoryExcelVo> categoryList);
}
