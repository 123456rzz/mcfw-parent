package org.mengchong.mcfw.pay.service;



import org.mengchong.mcfw.model.entity.pay.PaymentInfo;

import java.util.Map;

public interface PaymentInfoService {

    //1 保存支付信息
    PaymentInfo savePaymentInfo(String orderNo);

    //2 更新支付信息
    void updatePaymentStatus(Map<String, String> paramMap ,Integer payType);
}