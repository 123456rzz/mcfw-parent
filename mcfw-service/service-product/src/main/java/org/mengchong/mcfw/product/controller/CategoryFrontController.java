package org.mengchong.mcfw.product.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.product.service.CategoryFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value="/api/product/category")
//@CrossOrigin//解决跨域
//@SuppressWarnings({"unchecked", "rawtypes"})
public class CategoryFrontController {

   @Autowired
   private CategoryFrontService categoryFrontService;

   /**
    *
    */
   @Operation(summary = "获取分类树形数据")
   @GetMapping("findCategoryTree")
   public Result<List<Category>> findCategoryTree(){
      //
      List<Category> list = categoryFrontService.findCategoryTree();
      return Result.build(list,  ResultCodeEnum.SUCCESS);
   }

}