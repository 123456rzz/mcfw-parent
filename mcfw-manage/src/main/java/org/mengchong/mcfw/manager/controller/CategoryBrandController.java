package org.mengchong.mcfw.manager.controller;



import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.mengchong.mcfw.manager.service.CategoryBrandService;
import org.mengchong.mcfw.model.dto.product.CategoryBrandDto;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.entity.product.CategoryBrand;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {
        @Autowired
        private CategoryBrandService categoryBrandService;

    /**
     *   //1 分类品牌条件分页查询
     * @param page 当前页
     * @param limit   每页显示记录数
     * @param categoryBrandDto 前端提交过来的查询条件数据
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page, @PathVariable Integer limit, CategoryBrandDto categoryBrandDto){
        //调用service中的方法进行条件查询
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page,limit,categoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     *   // 2 分类品牌添加
     * @param categoryBrand  分类品牌对象
     * @return
     */
    @Operation(summary = "分类品牌添加")
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        //调用services中的添加方法
        categoryBrandService.save(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //3  分类品牌修改
    @Operation(summary = "分类品牌修改")
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //4  分类品牌删除
    @Operation(summary = "分类品牌删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *    // 5  根据分类ID查询品牌数据
     * @param categoryId
     * @return
     */
    @Operation(summary = "根据分类ID查询品牌数据")
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }


}
