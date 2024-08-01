package org.mengchong.mcfw.product.service.impl;




import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.entity.product.Brand;
import org.mengchong.mcfw.product.mapper.BrandFrontMapper;
import org.mengchong.mcfw.product.mapper.PetFrontMapper;
import org.mengchong.mcfw.product.service.BrandFrontService;
import org.mengchong.mcfw.product.service.PetFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

// 接口实现类
@Service
public class PetFrontServiceImpl implements PetFrontService {
    
	@Autowired
	private PetFrontMapper petFrontMapper;

//    @Override
//    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
//        return null;
//    }
//
//    @Override
//    public void save(Brand brand) {
//
//    }
//
//    @Override
//    public void updateBrand(Brand brand) {
//
//    }
//
//    @Override
//    public void deleteById(Long brandId) {
//
//    }


    /**
     *  //1  查询所有品牌
     * @return
     */
    //缓存带有该注解的方法的返回结果
    //value = "brandList"：表示这个缓存使用的名称是"brandList"，可以定义多个不同的缓存名称。
    //unless="#result.size() == 0"：这个表达式的意思是当方法返回值的size等于0时
    //即结果为空时，不进行缓存，即不缓存空的结果。
    //这是为了避免缓存的是空对象或空集合导致的不必要的开销和缓存污染问题。
	@Cacheable(value = "petList", unless="#result.size() == 0")
    @Override
    public List<PetInfo> findAll() {
        return petFrontMapper.findAll();
    }

}