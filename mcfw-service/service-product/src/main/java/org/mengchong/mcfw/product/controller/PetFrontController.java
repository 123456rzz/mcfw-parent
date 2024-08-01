package org.mengchong.mcfw.product.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.product.service.PetFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "宠物管理")
@RestController
@RequestMapping(value="/api/pet/pet")
//@SuppressWarnings({"unchecked", "rawtypes"})
public class PetFrontController {
   
   @Autowired
   private PetFrontService petFrontService;


   /**
    *   //1  查询所有品牌
    * @return
    */
   @Operation(summary = "获取全部宠物信息")
   @GetMapping("findAll")
   public Result<List<PetInfo>> findAll() {
      List<PetInfo> list = petFrontService.findAll();
      return Result.build(list, ResultCodeEnum.SUCCESS);
   }

}