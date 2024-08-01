package org.mengchong.mcfw.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.order.OrderInfo;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    //1 保存订单信息
    void save(OrderInfo orderInfo);


    //3 根据订单id获取订单详情信息
    OrderInfo getById(Long orderId);

    // 5  获取订单分页列表
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
    // 4   根据orderNo查询订单信息
    OrderInfo getByOrderNo(String orderNo) ;

    //远程调用：更新订单状态
    void updateById(OrderInfo orderInfo);
}