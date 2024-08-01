package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.ProductDetailsMapper;
import org.mengchong.mcfw.manager.mapper.ProductMapper;
import org.mengchong.mcfw.manager.mapper.ProductSkuMapper;
import org.mengchong.mcfw.manager.service.ProductService;
import org.mengchong.mcfw.model.dto.product.ProductDto;
import org.mengchong.mcfw.model.entity.product.Product;
import org.mengchong.mcfw.model.entity.product.ProductDetails;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper ;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     *     //1 商品条件分页查询
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param productDto 查询条件
     * @return
     */
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page , limit) ;
        List<Product> productList =  productMapper.findByPage(productDto) ;
        return new PageInfo<>(productList);
    }

    /**
     *     //2  商品新增
     * @param product
     */
    @Transactional
    @Override
    public void save(Product product) {
        // 1、保存商品级别数据  product表
        product.setStatus(0);              // 设置上架状态为0
        product.setAuditStatus(0);         // 设置审核状态为0
        productMapper.save(product);

        //2、保存商品sku列表数据 product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i=0 ; i< productSkuList.size() ; i++) {
            // 获取ProductSku对象
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);       // 构建skuCode
            productSku.setProductId(product.getId());               // 设置商品id
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);                               // 设置销量
            productSku.setStatus(0);
            productSkuMapper.save(productSku);                    // 保存数据

        }

        // 保存商品详情数据  product_details表
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);

    }

    /**
     *     //3 商品查询
     * @param id
     * @return
     */
    @Override
    public Product getById(Long id) {

        // 根据id查询商品数据
        Product product = productMapper.selectById(id);

        // 根据商品的id查询sku数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);

        // 根据商品的id查询商品详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());

        // 返回数据
        return product;
    }

    /**
     *     //4 商品修改
     * @param product
     */
    @Transactional
    @Override
    public void updateById(Product product) {

        // 修改商品基本数据
        productMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList(); //sku列表集合
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        // 修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);

    }


    /**
     *     //5 商品删除
     * @param id
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id);                   // 根据id删除商品基本数据
        productSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        productDetailsMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    /**
     *     //6 商品审核
     * @param id
     * @param auditStatus
     */
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        //新建商品实例对象
        Product product = new Product();
        //设置商品id
        product.setId(id);
        //审核状态1通过
        if(auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    /**
     *     //7 商品上架下架
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        //新建商品实例对象
        Product product = new Product();
        //设置商品id
        product.setId(id);
        // 审核状态1通过
        if(status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }



}
