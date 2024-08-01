package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.BrandService;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandservice;

    //列表分页
    @Log(title = "品牌列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit){
        PageInfo<Brand> pageInfo= brandservice.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

        /**
         *     品牌添加
         * @param brand
         * @return
         */
        @PostMapping("/save")
        public Result save(@RequestBody Brand brand) {
            brandservice.save(brand);
            return Result.build(null, ResultCodeEnum.SUCCESS);
        }

    /**
     *  //     3 品牌修改
     * @param brand 对象
     * @return
     */
    @PutMapping("/updateBrand")
    public Result updateBrand(@RequestBody Brand brand){
        brandservice.updateBrand(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 品牌删除
     * @param brandId 品牌id
     * @return
     */
    @DeleteMapping("/deleteById/{brandId}")
    public Result deleteById(@PathVariable("brandId") Long brandId){
        brandservice.deleteById(brandId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    // 5 查询所有品牌
    @GetMapping("/findAll")
    public Result findAll(){
        List<Brand> list = brandservice.findAll();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }


}
