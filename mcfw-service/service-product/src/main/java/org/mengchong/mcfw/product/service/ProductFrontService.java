package org.mengchong.mcfw.product.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.h5.ProductSkuDto;
import org.mengchong.mcfw.model.dto.product.SkuSaleDto;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.mengchong.mcfw.model.vo.h5.ProductItemVo;

import java.util.List;

/**
 * @author ljl
 * @create 2023-11-01-23:41
 */
// 业务接口
public interface ProductFrontService {

    //  1 畅销商品列表
    List<ProductSku> findProductSkuBySale();
    //2 商品分页查询
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    // 2  商品详情
    ProductItemVo item(String skuId);
    //3   获取商品sku信息
    ProductSku getBySkuId(Long skuId);
    //4  更新商品销量
    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}