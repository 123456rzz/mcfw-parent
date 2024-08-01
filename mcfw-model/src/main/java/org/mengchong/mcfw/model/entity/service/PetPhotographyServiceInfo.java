package org.mengchong.mcfw.model.entity.service;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-21 
 */

@Data
@Schema(description = "宠物摄影服务实体类")
public class PetPhotographyServiceInfo extends BaseEntity {

//	private static final long serialVersionUID =  4058108985248494110L;

	/**
	 * 服务id
	 */
	@Schema(description = "服务id")
	private Long serviceId;

	/**
	 * 服务概要
	 */
	@Schema(description = "服务概要")
	private String serviceSummary;

	/**
	 * 摄影服务类型
	 */
	@Schema(description = "摄影服务类型")
	private String trainedPetTypes;

	/**
	 * 拍摄地点
	 */
	@Schema(description = "拍摄地点")
	private String trainingExperience;

	/**
	 * 拍摄风格
	 */
	@Schema(description = "拍摄风格")
	private String trainingCourseClassification;

	/**
	 * 运输设备
	 */
	@Schema(description = "运输设备")
	private String trainingMethodDescription;



}
