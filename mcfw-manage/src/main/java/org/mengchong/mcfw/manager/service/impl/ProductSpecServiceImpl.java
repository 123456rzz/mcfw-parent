package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.ProductSpecMapper;
import org.mengchong.mcfw.manager.service.ProductSpecService;
import org.mengchong.mcfw.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper ;


    /**
     *    //1 商品规格条件分页查询
     * @param page 当前页数
     * @param limit 每页记录数
     * @return
     */
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        //设置分页参数
        PageHelper.startPage(page , limit) ;
        //调用mapper中的方法进行条件查询，根据查询条件查询所有数据，返回list集合
        List<ProductSpec> productSpecList = productSpecMapper.findByPage();
        //通过pageInfo返回每一页需要的数据
        return new PageInfo<>(productSpecList);
    }

    /**
     *  //    2 商品规格添加
     * @param productSpec  商品规格对象
     */
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec) ;
    }

    /**
     *   //    3 商品规格修改
     * @param productSpec
     */
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    /**
     *     //   4 商品规格删除
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    /**
     *      //   5 商品规格查询
     * @return
     */
    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }


}
