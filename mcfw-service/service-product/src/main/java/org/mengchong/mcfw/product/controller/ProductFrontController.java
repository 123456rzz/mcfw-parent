package org.mengchong.mcfw.product.controller;



import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.model.dto.h5.ProductSkuDto;
import org.mengchong.mcfw.model.dto.product.SkuSaleDto;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.mengchong.mcfw.model.entity.user.UserBrowseHistory;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.h5.ProductItemVo;
import org.mengchong.mcfw.product.service.ProductFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductFrontController {
	
	@Autowired
	private ProductFrontService productFrontService;


//	@Autowired
//	private UserFeignClient userFeignClient;

	/**
	 *   //1 商品分页查询
	 * @param page 当前页
	 * @param limit 每页记录数
	 * @param productSkuDto 封装前端所传递过来的查询参数
	 * @return
	 */
	@Operation(summary = "分页查询")
	@GetMapping(value = "/{page}/{limit}")
	public Result<PageInfo<ProductSku>> findByPage(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Integer page,
												   @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Integer limit,
												   @Parameter(name = "productSkuDto", description = "搜索条件对象", required = false) ProductSkuDto productSkuDto) {
		//  调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
		PageInfo<ProductSku> pageInfo = productFrontService.findByPage(page, limit, productSkuDto);
		return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
	}

	/**
	 * // 2  商品详情
	 * @param skuId  商品skuId
	 * @return
	 */
	@Operation(summary = "商品详情")
	@GetMapping("item/{skuId}")
	public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable String skuId) {
		//封装接口返回的数据对象
		ProductItemVo productItemVo = null;
//		if (!"undefined".equals(skuId)) {
//			// 如果没有传入有效的skuId，可以返回收藏最多的商品信息
//			//远程调用查询收藏最多的商品
//			productItemVo = productFrontService.item(skuId);
//		}else {
//			//远程调用获取浏览量最多的商品
//			UserBrowseHistory browseHistory = userFeignClient.getByBrowseHistory();
//			Long id = browseHistory.getSkuId();
//			String skuidString = String.valueOf(id);
//			productItemVo = productFrontService.item(skuidString);
//		}

		return Result.build(productItemVo , ResultCodeEnum.SUCCESS);
	}

	/**
	 * @Description: 3 远程调用：根据商品skuId获取商品sku信息
	 * @param skuId
	 */
    @Operation(summary = "获取商品sku信息")
	@GetMapping("getBySkuId/{skuId}")
	public ProductSku getBySkuId(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
		ProductSku productSku = productFrontService.getBySkuId(skuId);
	return productSku;
}

	/**
	 *  //4  更新商品销量
	 * @param skuSaleDtoList
	 * @return
	 */
	@Operation(summary = "更新商品sku销量")
	@PostMapping("updateSkuSaleNum")
	public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
		return productFrontService.updateSkuSaleNum(skuSaleDtoList);
	}

}