package org.mengchong.mcfw.order.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.order.OrderItem;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    //1  保存订单项信息
    void save(OrderItem orderItem);

    //2 查询订单项列表
    List<OrderItem> findByOrderId(Long id);
}