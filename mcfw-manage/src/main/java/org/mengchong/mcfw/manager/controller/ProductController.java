package org.mengchong.mcfw.manager.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.manager.service.ProductService;
import org.mengchong.mcfw.model.dto.product.ProductDto;
import org.mengchong.mcfw.model.entity.product.Product;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ljl
 * @create 2023-10-29-16:02
 */

@Tag(name = "商品管理接口")
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService ;

    /**
     *  //1 商品条件分页查询
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param productDto 使用@Requestbody封装前端提交过来的查询条件
     * @return
     */
    @Operation(summary = "条件分页查询接口")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);
        //        查询商品信息，并返回成功的结果
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * //2  商品新增
     * @param product
     * @return
     */
    @Operation(summary = "新增商品保存接口")
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *  //3 商品查询
     * @param id 商品id
     * @return
     */
    @Operation(summary = "查询商品详情_数据回显")
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * //4 商品修改
     * @param product 商品对象
     * @return
     */
    @Operation(summary = "修改_保存商品数据接口")
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * //5 商品删除
     * @param id
     * @return
     */
    @Operation(summary = "删除商品数据")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *  //6 商品审核
     * @param id
     * @param auditStatus
     * @return
     */
    @Operation(summary = "商品审核")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * //7 商品上架下架
     * @param id 商品id
     * @param status 商品状态
     * @return
     */
    @Operation(summary = "商品上架下架")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
