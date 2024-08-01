package org.mengchong.mcfw.pay.service.impl;

import com.alibaba.fastjson.JSON;

import org.mengchong.mcfw.feign.order.OrderFeignClient;
import org.mengchong.mcfw.feign.product.ProductFeignClient;
import org.mengchong.mcfw.model.dto.product.SkuSaleDto;
import org.mengchong.mcfw.model.entity.order.OrderInfo;
import org.mengchong.mcfw.model.entity.order.OrderItem;
import org.mengchong.mcfw.model.entity.pay.PaymentInfo;
import org.mengchong.mcfw.pay.mapper.PaymentInfoMapper;
import org.mengchong.mcfw.pay.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper ;

    @Autowired
    private OrderFeignClient orderFeignClient ;

    @Autowired
    private ProductFeignClient productFeignClient ;

    /**
     *  //1 保存支付信息
     * @param orderNo
     * @return
     */
    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {

        // 根据订单号查询支付信息数据，如果已经已经存在了就不用进行保存(一个订单支付失败以后可以继续支付)
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        //判断支付记录是否存在
        if(null == paymentInfo) {
            //远程调用获取订单信息
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            //把orderinfo获取数据封装到paymentInfo里面
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for(OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            //添加
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }

    /**
     *  //2 更新支付信息
     * @param map
     * @param payType
     */
    @Override
    public void updatePaymentStatus(Map<String, String> map ,Integer payType) {
        // 查询PaymentInfo
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(map.get("out_trade_no"));
        if (paymentInfo.getPaymentStatus() == 1) {
            return;
        }

        //更新支付信息
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(map.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(map));
        paymentInfoMapper.updateById(paymentInfo);

        // 3、更新订单的支付状态
        orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo() , payType) ;

        // 4、更新商品销量
        OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo()).getData();
        List<SkuSaleDto> skuSaleDtoList = orderInfo.getOrderItemList().stream().map(item -> {
            SkuSaleDto skuSaleDto = new SkuSaleDto();
            skuSaleDto.setSkuId(item.getSkuId());
            skuSaleDto.setNum(item.getSkuNum());
            return skuSaleDto;
        }).collect(Collectors.toList());
        System.out.println(skuSaleDtoList);

        productFeignClient.updateSkuSaleNum(skuSaleDtoList) ;
    }


}