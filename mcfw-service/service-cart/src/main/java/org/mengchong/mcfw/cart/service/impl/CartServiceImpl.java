package org.mengchong.mcfw.cart.service.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSON;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.mengchong.mcfw.cart.service.CartService;
import org.mengchong.mcfw.feign.product.ProductFeignClient;
import org.mengchong.mcfw.model.entity.h5.CartInfo;
import org.mengchong.mcfw.model.entity.product.ProductSku;
import org.mengchong.mcfw.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ljl
 * @create 2023-11-04-1:21
 */
//业务接口实现
@Service
public class CartServiceImpl implements CartService {

    //注入redis对象，用来操作redis数据库
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }

    /**
     *   //1 添加购物车
     * @param skuId 商品sku的id值
     * @param skuNum 商品数量
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {

        //1.必须为登录状态，获取当前登录用户的id（作为redis的hash类型的key值）
        //从ThreadLocal获取用户信息就可以
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建hash类型key名称
        String cartKey = getCartKey(userId);

        //2.从redis中获取购物车数据，根据用户id+skuid获取（hash类型key+field+value）
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null ;
        //3.如果购物车存在添加的商品，那么就将商品数量相加
        if(cartInfoObj != null) {       //  如果购物车中有该商品，则商品数量 相加！
            cartInfo = JSON.parseObject(cartInfoObj.toString() , CartInfo.class) ;
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            //等于1表示是选中状态
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }else {
            //4.当购物车中没用该商品的时候，则直接添加到购物车
            //远程调用实现：通过nacos+openFeign 实现，根据skuid获取商品的sku信息
            cartInfo = new CartInfo();

            // 根据skuid获取到商品sku信息，购物车数据是从商品详情得到 {skuInfo}
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());

        }

        // 将商品数据存储到购物车中
        redisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
    }

    /**
     *  // 2  购物车列表查询
     * @return
     */
    @Override
    public List<CartInfo> getCartList() {

        // 用map()方法将每个JSON字符串转换为CartInfo对象，
        // 即将商品信息从JSON字符串反序列化为Java对象。然后，
        // 使用sorted()方法根据商品的创建时间进行排序，以便后续的展示。
        // 最后，使用collect()方法将处理后的CartInfo对象收集到一个新的List中，并返回该列表作为结果。
        // 如果购物车为空，则返回一个空的ArrayList对象作为结果。
        // 获取当前登录的用户信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 获取数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
//        System.out.println("cartInfoList:"+cartInfoList);
        if (!CollectionUtils.isEmpty(cartInfoList)) {
            List<CartInfo> infoList = cartInfoList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
//            System.out.println("infoList"+infoList);
            return infoList ;
        }

        return new ArrayList<>() ;
    }

    /**
     *  // 3 删除购物车商品
     * @param skuId
     */
    @Override
    public void deleteCart(Long skuId) {

        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象
        redisTemplate.opsForHash().delete(cartKey ,String.valueOf(skuId)) ;
    }

    /**
     *      //4 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     */

    @Override
    public void checkCart(Long skuId, Integer isChecked) {

        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //判断key是否包含filed
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if(hasKey) {
            String cartInfoJSON = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
        }

    }

    /**
     * //5 完成购物车商品的全选
     */
    public void allCheckCart(Integer isChecked) {

        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        // 获取所有的购物项数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(objectList)) {
            objectList.stream().map(cartInfoJSON -> {
                CartInfo cartInfo = JSON.parseObject(cartInfoJSON.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo ;
            }).forEach(cartInfo -> redisTemplate.opsForHash().put(cartKey , String.valueOf(cartInfo.getSkuId()) , JSON.toJSONString(cartInfo)));

        }
    }

    /**
     *    //6 清空购物车
     */
    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        redisTemplate.delete(cartKey);
    }


    /**
     * 选中的商品
     * // 7  远程调用订单结算时候使用，获取购物车选中商品列表
     * @return
     */
    @Override
    public List<CartInfo> getAllCkecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);       // 获取所有的购物项数据
        if(!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList ;
        }
        return new ArrayList<>() ;
    }

    /**
     *    //8  远程调用使用，删除生成订单的购物车商品
     */
    @Override
    public void deleteChecked() {
        //获取userid，构建key
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //根据key获取redis所有的value
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);       // 删除选中的购物项数据
        //删除选中商品
        if(!CollectionUtils.isEmpty(objectList)) {
            objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey , String.valueOf(cartInfo.getSkuId())));
        }
    }


}
