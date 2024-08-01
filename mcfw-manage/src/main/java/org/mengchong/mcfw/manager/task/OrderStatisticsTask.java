package org.mengchong.mcfw.manager.task;

import cn.hutool.core.date.DateUtil;

import lombok.extern.slf4j.Slf4j;
import org.mengchong.mcfw.manager.mapper.OrderInfoMapper;
import org.mengchong.mcfw.manager.mapper.OrderStatisticsMapper;
import org.mengchong.mcfw.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ljl
 * @create 2023-10-30-18:50
 */
// com.atguigu.spzx.manager.task
@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }

}
