package org.mengchong.mcfw.order.service;


import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.h5.OrderInfoDto;
import org.mengchong.mcfw.model.entity.order.OrderInfo;
import org.mengchong.mcfw.model.vo.h5.TradeVo;

//业务接口
public interface OrderInfoService {
    TradeVo getTrade();

    //2 提交订单
    Long submitOrder(OrderInfoDto orderInfoDto);

    //3  根据订单id获取订单信息
    OrderInfo getOrderInfo(Long orderId);

    // 4  立即购买
    TradeVo buy(Long skuId);

    //5 获取订单分页列表
    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    //远程调用：根据订单编号获取订单信息
    OrderInfo getByOrderNo(String orderNo) ;

    //远程调用：更新订单状态
    void updateOrderStatus(String orderNo, Integer orderStatus);


}