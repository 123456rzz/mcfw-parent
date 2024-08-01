package org.mengchong.mcfw.product.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.h5.IndexVo;
import org.mengchong.mcfw.product.service.CategoryFrontService;
import org.mengchong.mcfw.product.service.ProductFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页接口
 * @author ljl
 * @create 2023-11-01-20:51
 */

@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
//@SuppressWarnings({"unchecked", "rawtypes"})
//@CrossOrigin//解决跨域
public class IndexFrontController {


    @Autowired
    private CategoryFrontService categoryFrontService;

    @Autowired
    private ProductFrontService productFrontService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData(){
        //所有的一级分类
        List<Category> categoryList = categoryFrontService.findOneCategory();
        //根据销量排序，获取前20条记录
        List<ProductSku> productSkuList = productFrontService.findProductSkuBySale();
        //封装数据
        IndexVo indexVo = new IndexVo() ;
        indexVo.setCategoryList(categoryList); // 一级分类的类别数据
        indexVo.setProductSkuList(productSkuList); //畅销商品列表数据
        return Result.build(indexVo,ResultCodeEnum.SUCCESS);
    }

}