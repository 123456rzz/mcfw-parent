package org.mengchong.mcfw.model.entity.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-21 
 */


@Data
@Schema(description = "宠物服务请求实体类")
public class PetServiceRequest  extends BaseEntity {

	/**
	 * 用户id
	 */
	@Schema(description = "用户id")
	private Long userId;

	/**
	 * 宠物id
	 */
	@Schema(description = "宠物id")
	private Long petId;

	/**
	 * 服务类型id
	 */
	@Schema(description = "服务类型id")
	private Long serviceTypeId;

	/**
	 * 需要服务开始时间
	 */
	@Schema(description = "需要服务开始时间")
	private Date serviceStartTime;

	/**
	 * 需要服务结束时间
	 */
	@Schema(description = "需要服务结束时间")
	private Date serviceEndTime;

	/**
	 * 所在位置
	 */
	@Schema(description = "所在位置")
	private String location;

	/**
	 * 保姆注意事项
	 */
	@Schema(description = "保姆注意事项")
	private String nannyPrecautions;

	/**
	 * 载送服务（需要、不需要）
	 */
	@Schema(description = "载送服务（需要、不需要）")
	private String transportationService;





}
