package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.CategoryBrandMapper;
import org.mengchong.mcfw.manager.service.CategoryBrandService;
import org.mengchong.mcfw.model.dto.product.CategoryBrandDto;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /**
     *   // 1 分类品牌条件分页查询
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param categoryBrandDto 前端提交过来的查询条件数据
     * @return 返回每一页需要的数据
     */
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        //设置分页参数，传入当前页和每页记录数
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<CategoryBrand> list = categoryBrandMapper.findByPage(categoryBrandDto);
        //通过pageInfo返回每一页需要的数据
        PageInfo<CategoryBrand> pageInfo =new PageInfo<>(list);
        return pageInfo;
    }

    //2 分类品牌添加
    @Override
    public void save(CategoryBrand categoryBrand){
        categoryBrandMapper.save(categoryBrand);
    }

    //3  分类品牌修改
    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand) ;
    }

    //4  分类品牌删除
    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id) ;
    }

    // 5  根据分类ID查询品牌数据
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }


}
