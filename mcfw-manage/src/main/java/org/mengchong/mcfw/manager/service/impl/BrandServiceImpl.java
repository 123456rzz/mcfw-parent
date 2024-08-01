package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.BrandMapper;
import org.mengchong.mcfw.manager.service.BrandService;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl  implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    //列表查询
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit){

        //设置分页参数：当前页page，每页记录数limit
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行分页查询
        List<Brand> list=brandMapper.findByPage();
        PageInfo<Brand> pageInfo =new PageInfo<>(list);
        //返回list集合查询结果
        return pageInfo;
    }

    //品牌添加
    @Override
    public void save(Brand brand){
        brandMapper.save(brand);
    }

    /**
     * //    3 品牌修改
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand ){
        //调用保存方法将修改的数据存到数据库
        brandMapper.update(brand);
    }

    /**
     * //    4 品牌删除
     * @param brandId
     */
    @Override
    public void deleteById(Long brandId){
        brandMapper.delete(brandId);
    }

    //查询所有品牌
    @Override
    public List<Brand> findAll(){
        return  brandMapper.findByPage();
//        return  brandMapper.findByPage();
    }
}
