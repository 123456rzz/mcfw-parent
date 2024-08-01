package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.order.OrderStatisticsDto;
import org.mengchong.mcfw.model.entity.order.OrderStatistics;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    //1

    void insert(OrderStatistics orderStatistics);
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);


}