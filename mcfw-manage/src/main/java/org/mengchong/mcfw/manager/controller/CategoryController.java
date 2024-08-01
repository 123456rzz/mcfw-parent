package org.mengchong.mcfw.manager.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.mengchong.mcfw.manager.service.CategoryService;
import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    /**
     * //分类列表，每次查询一层数据
     * // SELECT*FROM category WHERE parent_id=id
     * @param id 父分类id
     * @return
     */
    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable Long id) {
        //调用services中的方法进行查询,
        List<Category> list = categoryService.findCategoryList(id);

        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * 导出
     * @param response
     */
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }



}
