package org.mengchong.mcfw.pay.service;

public interface AlipayService {

    //1 支付宝下单
    String submitAlipay(String orderNo);
}