package org.mengchong.mcfw.model.entity.service;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;


/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-21 
 */

@Data
@Schema(description = "宠物服务实体类")
public class PetServeInfo  extends BaseEntity {


	/**
	 * 用户id
	 */
	@Schema(description = "用户id")
	private Long userId;

	/**
	 * 服务类型
	 */
	@Schema(description = "服务类型")
	private String serviceType;

	/**
	 * 服务名称
	 */
	@Schema(description = "服务名称")
	private String serviceName;

	/**
	 * 服务概要
	 */
	@Schema(description = "服务概要")
	private String serviceSummary;

	/**
	 * 一次可以在家里看几只宠物
	 */
	@Schema(description = "一次可以在家里看几只宠物")
	private String countPets;

	/**
	 * 接受的宠物类型
	 */
	@Schema(description = "接受的宠物类型")
	private String acceptedPetTypes;

	/**
	 * 接受的宠物大小
	 */
	@Schema(description = "接受的宠物大小")
	private String acceptedPetSizes;

	/**
	 * 载送宠物距离
	 */
	@Schema(description = "载送宠物距离")
	private String transportPetDistance;

	/**
	 * 监督级别
	 */
	@Schema(description = "监督级别")
	private String supervisionLevel;
	/**
	 * 监督级别
	 */
	@Schema(description = "无人监督宠物位置")
	private String supervisionLocation;


	/**
	 * 宠物睡觉区域
	 */
	@Schema(description = "宠物睡觉区域")
	private String petSleepingArea;

	/**
	 * 提供大小便次数
	 */
	@Schema(description = "提供大小便次数")
	private String providedUrinationTimes;

	/**
	 * 提供步行次数
	 */
	@Schema(description = "提供步行次数")
	private String numberWalks;

	/**
	 * 每次散步时长
	 */
	@Schema(description = "每次散步时长")
	private String walkLength;

	/**
	 * 住宅类型
	 */
	@Schema(description = "住宅类型")
	private String residentialType;

	/**
	 * 允许最后一刻预订（0是，1否） 
	 */
	@Schema(description = "允许最后一刻预订")
	private Long lastMinuteBooking;

	/**
	 * 首选搜索位置
	 */
	@Schema(description = "首选搜索位置")
	private String searchLocation;

	/**
	 * 价格
	 */
	@Schema(description = "价格")
	private Double price;

	/**
	 * 价格明细描述
	 */
	@Schema(description = "价格明细描述")
	private String priceDetails;

	/**
	 * 收费标准
	 */
	@Schema(description = "收费标准")
	private String chargingStandards;

	/**
	 * 服务位置
	 */
	@Schema(description = "服务位置")
	private String serviceLocation;

	/**
	 * 支付方式 （0支付宝，1微信，2银行卡，3其他
	 */
	@Schema(description = "支付方式")
	private Long paymentMethod;





}
