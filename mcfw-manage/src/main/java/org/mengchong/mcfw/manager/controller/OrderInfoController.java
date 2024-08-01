package org.mengchong.mcfw.manager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.manager.service.OrderInfoService;
import org.mengchong.mcfw.model.dto.order.OrderStatisticsDto;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @create 2023-10-30-18:54
 */

@Tag(name = "统计查询接口")
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService ;

    /**
     * 1 获取订单统计数据
     * @param orderStatisticsDto 对象
     * @return
     */
    @GetMapping("/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //调用orderInfoService的getOrderStatisticsData方法，传入orderStatisticsDto参数，获取订单统计数据并将结果保存在orderStatisticsVo对象中。
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto) ;
        //将获取到的订单统计数据封装成Result对象，设置成功的状态码，并返回给调用方。
        return Result.build(orderStatisticsVo , ResultCodeEnum.SUCCESS) ;
    }

}
