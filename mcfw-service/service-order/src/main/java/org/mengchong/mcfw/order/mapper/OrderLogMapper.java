package org.mengchong.mcfw.order.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.order.OrderLog;

@Mapper
public interface OrderLogMapper {

    //保存订单记录日志
    void save(OrderLog orderLog);
}