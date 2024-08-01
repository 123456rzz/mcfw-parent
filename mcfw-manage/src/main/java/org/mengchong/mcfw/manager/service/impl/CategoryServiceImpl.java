package org.mengchong.mcfw.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.mengchong.mcfw.common.exception.GuiguException;
import org.mengchong.mcfw.manager.mapper.CategoryMapper;
import org.mengchong.mcfw.manager.service.CategoryService;
import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.product.CategoryExcelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * //列表查询
     * @param id
     * @return
     */
    @Override
    public List<Category> findCategoryList(Long id) {
        //1 根据id条件值进行查询，返回List集合 // SELECT*FROM category WHERE parent id=id
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(id);
        //2 遍历返四List集合，
        //判断每个分类是否有下一层分类，如果有设置 hashChildren = true  hashChildren是element-plus自带的属性
        if(!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                //判断每个分类是否有下一层分类
                // SELECT count(*) FROM category WHERE parent id=1
                int count = categoryMapper.selectCountByParentId(category.getId());
                if(count > 0) { //下一层分类
                    category.setHasChildren(true);
                }else{
                    category.setHasChildren(false);
                }
            });
        }
        return categoryList;
    }

    /**
     *2 导出
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        try{
            //1 设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode 可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode( "分类数据","UTF-8");
            //设置响应头信息 Content-disposition
            response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
            //2 调用mapper方法查询所有分类，返回List集合
            List<Category> categoryList = categoryMapper.findAll();
            //最终数据list集合
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            //list<Category> --  List<CategoryExcelVo>
            for(Category category:categoryList){
                //
                CategoryExcelVo categoryExcelVo=new CategoryExcelVo();
                //
                BeanUtils.copyProperties(category,categoryExcelVo);
                //
                categoryExcelVoList.add(categoryExcelVo);
            }
            //3 调用EasyExcel的write方法完成与操作
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

    }


}
