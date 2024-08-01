package org.mengchong.mcfw.manager.service;


import org.mengchong.mcfw.model.dto.order.OrderStatisticsDto;
import org.mengchong.mcfw.model.vo.order.OrderStatisticsVo;

/**
 * @author ljl
 * @create 2023-10-30-22:56
 */
public interface OrderInfoService {
    //1 获取订单统计数据
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);


}
