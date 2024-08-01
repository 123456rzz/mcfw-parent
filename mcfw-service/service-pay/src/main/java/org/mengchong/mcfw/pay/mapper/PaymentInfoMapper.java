package org.mengchong.mcfw.pay.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.pay.PaymentInfo;

@Mapper
public interface PaymentInfoMapper {
    //保存支付信息
    void save(PaymentInfo paymentInfo);

    // 通过订单号查询支付信息
    PaymentInfo getByOrderNo(String orderNo);

    //2 更新支付信息
    void updateById(PaymentInfo paymentInfo);
}