package org.mengchong.mcfw.order.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.feign.user.UserFeignClient;
import org.mengchong.mcfw.model.dto.h5.OrderInfoDto;
import org.mengchong.mcfw.model.entity.order.OrderInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.h5.TradeVo;
import org.mengchong.mcfw.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {
   
   @Autowired
   private OrderInfoService orderInfoService;

   @Autowired
   private UserFeignClient userFeignClient;

   @Operation(summary = "确认下单")
   @GetMapping("auth/trade")
   public Result<TradeVo> trade() {
      TradeVo tradeVo = orderInfoService.getTrade();
      return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
   }

   /**
    *  //2   提交订单
    * @param orderInfoDto 封装提交过来的订单数据
    * @return
    */
   @Operation(summary = "提交订单")
   @PostMapping("auth/submitOrder")
   public Result<Long> submitOrder(@Parameter(name = "orderInfoDto", description = "请求参数实体类", required = true) @RequestBody OrderInfoDto orderInfoDto) {
      Long orderId = orderInfoService.submitOrder(orderInfoDto);
      return Result.build(orderId, ResultCodeEnum.SUCCESS);
   }

   /**
    *  //3  根据订单id获取订单信息
    * @param orderId
    * @return
    */
   @Operation(summary = "获取订单信息")
   @GetMapping("auth/{orderId}")
   public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable Long orderId) {
      OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
      return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
   }

   /**
    * // 4  立即购买
    * @param skuId
    * @return
    */
   @Operation(summary = "立即购买")
   @GetMapping("auth/buy/{skuId}")
   public Result<TradeVo> buy(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable String skuId) {
      TradeVo tradeVo;
      if (skuId == null || skuId.isEmpty() || skuId.equals("NaN")) {
         tradeVo = orderInfoService.buy(userFeignClient.getByBrowseHistory().getSkuId());
      } else {
         tradeVo = orderInfoService.buy(Long.valueOf(skuId));
      }
      return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
   }

   /**
    *    // 5  获取订单分页列表
    * @param page 当前页码
    * @param limit 每页记录数
    * @param orderStatus 订单状态
    * @return
    */
   @Operation(summary = "获取订单分页列表")
   @GetMapping("auth/{page}/{limit}")
   public Result<PageInfo<OrderInfo>> list(
           @Parameter(name = "page", description = "当前页码", required = true)
           @PathVariable Integer page,

           @Parameter(name = "limit", description = "每页记录数", required = true)
           @PathVariable Integer limit,

           @Parameter(name = "orderStatus", description = "订单状态", required = false)
           @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
      PageInfo<OrderInfo> pageInfo = orderInfoService.findUserPage(page, limit, orderStatus);
      return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
   }

   //远程调用：根据订单编号获取订单信息
   @Operation(summary = "获取订单信息")
   @GetMapping("auth/getOrderInfoByOrderNo/{orderNo}")
   public Result<OrderInfo> getOrderInfoByOrderNo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable String orderNo) {
      OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo) ;
      return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
   }

   //远程调用：更新订单状态
   @Operation(summary = "获取订单分页列表")
   @GetMapping("auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
   public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo , @PathVariable(value = "orderStatus") Integer orderStatus) {
      orderInfoService.updateOrderStatus(orderNo , orderStatus);
      return Result.build(null , ResultCodeEnum.SUCCESS) ;
   }

}