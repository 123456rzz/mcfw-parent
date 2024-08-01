package org.mengchong.mcfw.feign.cart;

import org.mengchong.mcfw.model.entity.h5.CartInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ljl
 * @create 2023-11-05-1:20
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    public abstract List<CartInfo> getAllCkecked() ;

    //  远程调用使用，删除生成订单的购物车商品
    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public abstract Result deleteChecked() ;

}