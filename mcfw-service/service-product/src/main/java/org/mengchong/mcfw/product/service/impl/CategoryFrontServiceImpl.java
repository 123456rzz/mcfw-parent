package org.mengchong.mcfw.product.service.impl;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import org.mengchong.mcfw.model.entity.product.Category;
import org.mengchong.mcfw.product.mapper.CategoryFrontMapper;
import org.mengchong.mcfw.product.service.CategoryFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// 接口实现类
@Slf4j
@Service
public class CategoryFrontServiceImpl implements CategoryFrontService {

	@Autowired
	private CategoryFrontMapper categoryFrontMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;



    /**
     *    //  1   查询所有的一级分类数据
     * @return
     */
    @Override
    public List<Category> findOneCategory() {

        // 从Redis缓存中查询所有的一级分类数据
        String categoryListJSON = redisTemplate.opsForValue().get("category:one");
        if(!ObjectUtils.isEmpty(categoryListJSON)) {
            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
            log.info("从Redis缓存中查询到了所有的一级分类数据");
            return categoryList ;
        }

        List<Category> categoryList = categoryFrontMapper.findOneCategory();
        System.out.println(categoryList);
        log.info("从数据库中查询到了所有的一级分类数据");
        redisTemplate.opsForValue().set("category:one" ,
                JSON.toJSONString(categoryList) , 7 , TimeUnit.DAYS);
        return categoryList ;
    }

    // 2  查询所有分类，树型
    @Cacheable(value = "category" , key = "'all'")  //开启缓存注解功能 value: 缓存的名称  key: 缓存的key
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryFrontMapper.findAll();
        //查询所有分类，返回list集合
        List<Category> oneCategoryList = categoryList.stream().
                filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());
        //通过条件parentid=0得到所有的一级分类
        //遍历一级分类的集合，通过id=parentid ，得到下面的二级分类
        //遍历二级分类的集合，通过id=parentid ，得到下面的三级分类
        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}