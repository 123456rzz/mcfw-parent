package org.mengchong.mcfw.manager.service.impl;

import cn.hutool.core.date.DateUtil;

import org.mengchong.mcfw.manager.mapper.OrderStatisticsMapper;
import org.mengchong.mcfw.manager.service.OrderInfoService;
import org.mengchong.mcfw.model.dto.order.OrderStatisticsDto;
import org.mengchong.mcfw.model.entity.order.OrderStatistics;
import org.mengchong.mcfw.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper ;


     /**
     *从订单统计数据中获取日期列表和金额列表，并封装到OrderStatisticsVo对象中返回。
     * @param orderStatisticsDto
     * @return
     */
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {

        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto) ;

        //日期列表
        // orderStatisticsList.stream() ：将订单统计列表转换为流（Stream）以便对其中的元素进行操作。
        //map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")) ：
        // 对流中的每个元素（orderStatistics）执行指定操作，这里是调用 DateUtil.format() 方法将订单日期格式化为"yyyy-MM-dd"格式的字符串。
        //collect(Collectors.toList()) ：将流中经过处理后的元素收集到一个新的列表中，并返回这个列表作为结果，最终赋给 dateList 变量。
        List<String> dateList = orderStatisticsList.stream().map
                (orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表,将  orderStatisticsList  中的  TotalAmount  属性提取出来，存入一个新的  List<BigDecimal>  中。
        /**
         * 1. 使用流（Stream）对  orderStatisticsList  进行操作。
         * 2. 使用  map  方法，将  OrderStatistics  对象映射为其  TotalAmount  属性。
         * 3. 使用  collect  方法，将映射后的结果收集到一个新的  List<BigDecimal>  中
         */
        List<BigDecimal> amountList = orderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        // 返回数据
        return orderStatisticsVo;
    }
}