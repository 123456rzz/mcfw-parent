package org.mengchong.mcfw.model.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Schema(description = "宠物服务订单实体类")
public class McPetOrderInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "用户名称")
	private String userName;

	@Schema(description = "雇主id")
	private Long employerId;

	@Schema(description = "宠物id")
	private Long petId;

	@Schema(description = "订单号")
	private String orderNumber;

	@Schema(description = "使用的优惠券")
	private Long usedCoupon;

	@Schema(description = "原价金额")
	private BigDecimal originalPrice;

	@Schema(description = "支付方式【1->微信】")
	private Integer paymentMethod;

	@Schema(description = "订单状态【0->待付款；1->待发货；2->已发货；3->待用户收货，已完成；-1->已取消】")
	private Integer orderStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "取消订单时间")
	private Date cancelTime;

	@Schema(description = "取消订单原因")
	private String cancelReason;


}