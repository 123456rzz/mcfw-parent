package org.mengchong.mcfw.product.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.product.service.BrandFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "品牌管理")
@RestController
@RequestMapping(value="/api/product/brand")
//@SuppressWarnings({"unchecked", "rawtypes"})
public class BrandFrontController {
   
   @Autowired
   private BrandFrontService  brandFrontService;


   /**
    *   //1  查询所有品牌
    * @return
    */
   @Operation(summary = "获取全部品牌")
   @GetMapping("findAll")
   public Result<List<Brand>> findAll() {
      List<Brand> list = brandFrontService.findAll();
      return Result.build(list, ResultCodeEnum.SUCCESS);
   }

}