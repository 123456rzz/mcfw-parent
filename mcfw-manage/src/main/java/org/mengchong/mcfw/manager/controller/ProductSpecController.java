package org.mengchong.mcfw.manager.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.manager.service.ProductSpecService;
import org.mengchong.mcfw.model.entity.product.ProductSpec;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ljl
 * @create 2023-10-29-12:53
 */

@Tag(name = "商品规格管理")
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {


// 通过@Autowired注释注入了一个ProductSpecService实例
@Autowired
private ProductSpecService productSpecService ;

    /**
     * //1 商品规格条件分页查询
     * @param page 当前页数
     * @param limit 每页显示数量
     * @return 查询产品规格信息，并返回成功的结果
     */
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page, limit);
        //返回成功结果
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * //    2 商品规格添加
     * @param productSpec  商品规格对象
     * @return 返回成功的结果
     */
    @Operation(summary = "规格添加接口")
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *   //    3 商品规格修改
     * @param productSpec
     * @return
     */
    @Operation(summary = "规格修改接口")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *  //   4 商品规格删除
     * @param id   商品规格id
     * @return
     */
    @Operation(summary = "商品规格删除接口")
    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *  //   5 商品规格查询
     * @return 返回所有产品规格列表并构建成功结果
     */
    @Operation(summary = "商品规格查询")
    @GetMapping("findAll")
    public Result findAll() {

        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }




}
