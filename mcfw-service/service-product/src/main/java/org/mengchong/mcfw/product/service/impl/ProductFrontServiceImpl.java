package org.mengchong.mcfw.product.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.h5.ProductSkuDto;
import org.mengchong.mcfw.model.dto.product.SkuSaleDto;
import org.mengchong.mcfw.model.entity.product.Product;
import org.mengchong.mcfw.model.entity.product.ProductDetails;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.mengchong.mcfw.model.vo.h5.ProductItemVo;
import org.mengchong.mcfw.product.mapper.ProductDetailsFrontMapper;
import org.mengchong.mcfw.product.mapper.ProductFrontMapper;
import org.mengchong.mcfw.product.mapper.ProductSkuFrontMapper;
import org.mengchong.mcfw.product.service.ProductFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 接口实现类
@Service
public class ProductFrontServiceImpl implements ProductFrontService {

    @Autowired
    private ProductSkuFrontMapper productSkuFrontMapper;

    @Autowired
    private ProductFrontMapper productFrontMapper;
    @Autowired
    private ProductDetailsFrontMapper productDetailsFrontMapper;

    /**
     *  //  1 畅销商品列表
     * @return
     */
    @Override
    public List<ProductSku> findProductSkuBySale() {
        return productSkuFrontMapper.findProductSkuBySale();
    }

    // 接口实现类

    /**
     *  //2  商品分页查询
     * @param page 当前页
     * @param limit 每页显示条数
     * @param productSkuDto 商品列表搜索条件
     * @return
     */
    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productSkuFrontMapper.findByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    /**
     *  // 2  商品详情
     * @param skuId
     * @return
     */
    @Override
    public ProductItemVo item(String skuId) {
        //1、创建vo对象，封装最终的数据
        ProductItemVo productItemVo = new ProductItemVo();

        //2、根据skuid获取当前sku信息
        ProductSku productSku = productSkuFrontMapper.getById(Long.valueOf(skuId));

        //3、根据第二步获取sku，从sku获取productId，获取当前商品信息
        Product product = productFrontMapper.getById(productSku.getProductId());

        //4、获取商品详情信息
        ProductDetails productDetails = productDetailsFrontMapper.getByProductId(productSku.getProductId());

        //5、封装map集合
        Map<String,Object> skuSpecValueMap = new HashMap<>();
        //建立sku规格与skuId对应关系
        List<ProductSku> productSkuList = productSkuFrontMapper.findByProductId(productSku.getProductId());
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });
        //商品sku信息
        productItemVo.setProductSku(productSku);
        //商品信息
        productItemVo.setProduct(product);
        //商品规格对应商品skuId信息
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        //商品详情图片列表
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));

        //轮播图list集合
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        //商品规格信息
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));

        return productItemVo;
    }

    //3   获取商品sku信息
    @Override
    public ProductSku getBySkuId(Long skuId) {
        return productSkuFrontMapper.getById(skuId);
    }

    /**
     *  //4  更新商品销量
     * @param skuSaleDtoList
     * @return
     */
    @Transactional
    @Override
    public Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList) {
        if(!CollectionUtils.isEmpty(skuSaleDtoList)) {
            for(SkuSaleDto skuSaleDto : skuSaleDtoList) {
                productSkuFrontMapper.updateSale(skuSaleDto.getSkuId(), skuSaleDto.getNum());
            }
        }
        return true;
    }
}