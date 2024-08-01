package org.mengchong.mcfw.cart.service;



import org.mengchong.mcfw.model.entity.h5.CartInfo;

import java.util.List;

//业务接口
public interface CartService {

    //1 添加购物车
    void addToCart(Long skuId, Integer skuNum);
    // 2  购物车列表查询
    List<CartInfo> getCartList();
    // 3 删除购物车商品
    void deleteCart(Long skuId);
    //4 更新购物车商品选中状态
    void checkCart(Long skuId, Integer isChecked);
    //5 完成购物车商品的全选
    void allCheckCart(Integer isChecked);

    void clearCart();
    //7 远程调用订单结算时候使用，获取购物车选中商品列表
    List<CartInfo> getAllCkecked();

    //8  远程调用使用，删除生成订单的购物车商品
    void deleteChecked();
}