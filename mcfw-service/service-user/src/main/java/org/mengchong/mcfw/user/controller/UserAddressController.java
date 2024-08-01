package org.mengchong.mcfw.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.entity.base.Region;
import org.mengchong.mcfw.model.entity.user.UserAddress;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.user.service.UserAddressService;
import org.mengchong.mcfw.user.service.UserRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value="/api/user")
public class UserAddressController {
   
   @Autowired
   private UserAddressService userAddressService;

   @Autowired
   private UserRegionService userRegionService;

   /**
    * //1 获取用户地址
    * @return
    */
   @Operation(summary = "获取用户地址列表")
   @GetMapping("userAddress/auth/findUserAddressList")
   public Result<List<UserAddress>> findUserAddressList() {
      List<UserAddress> list = userAddressService.findUserAddressList();
      return Result.build(list , ResultCodeEnum.SUCCESS) ;
   }

   /**
    *   //2  获取地址信息
    * @param id
    * @return
    */
   @Operation(summary = "获取地址信息")
   @GetMapping("userAddress/getUserAddress/{id}")
   public UserAddress getUserAddress(@PathVariable Long id) {
      return userAddressService.getById(id);
   }


   @Operation(summary = "收货地址省市区显示")
   @GetMapping ("region/findByParentCode/{code}")
   public Result register(@PathVariable("code") Integer code) {
      List<Region> regionList = userRegionService.selectByParentCode(code);
      return Result.build(regionList , ResultCodeEnum.SUCCESS) ;
   }


   @Operation(summary = "用户收货地址修改")
   @PutMapping("userAddress/auth/updateById")
   public Result updateById(@RequestBody UserAddress userAddress) {
      userAddressService.updateByid(userAddress);
      return Result.build(null , ResultCodeEnum.SUCCESS) ;
   }

   @Operation(summary = "用户收货地址新增")
   @PostMapping("userAddress/auth/save")
   public Result save(@RequestBody UserAddress userAddress) {
      userAddressService.save(userAddress);
      return Result.build(null , ResultCodeEnum.SUCCESS) ;
   }

   @Operation(summary = "用户删除收货地址")
   @DeleteMapping("userAddress/auth/removeById/{id}")
   public Result removeById(@PathVariable Long id) {
      userAddressService.removeById(id);
      return Result.build(null , ResultCodeEnum.SUCCESS) ;
   }


}