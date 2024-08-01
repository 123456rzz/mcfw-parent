package org.mengchong.mcfw.model.entity.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-23 
 */

@Data
@Schema(description = "宠物培训服务实体类")
public class PetTrainServiceInfo  extends BaseEntity {


	/**
	 * 服务id
	 */
	@Schema(description = "用户id")
	private Long serviceId;

	/**
	 * 服务概要
	 */
	@Schema(description = "用户id")
	private String serviceSummary;

	/**
	 * 培训过的宠物类型
	 */
	@Schema(description = "用户id")
	private String trainedPetTypes;

	/**
	 * 培训经验
	 */
	@Schema(description = "用户id")
	private String trainingExperience;

	/**
	 * 培训课程分类
	 */
	@Schema(description = "用户id")
	private String trainingCourseClassification;

	/**
	 * 训练方法描述
	 */
	@Schema(description = "训练方法描述")
	private String trainingMethodDescription;

	/**
	 * 培训预期效果描述
	 */
	@Schema(description = "培训预期效果描述")
	private String trainingEffectDescription;

	/**
	 * 培训时长
	 */
	@Schema(description = "培训时长")
	private String trainingDuration;

	/**
	 * 紧急情况载送（0有，1无）
	 */
	@Schema(description = "紧急情况载送（0有，1无）")
	private String emergencyTransportation;

	/**
	 * 课程类型
	 */
	@Schema(description = "课程类型")
	private String courseType;

	/**
	 * 宠物要求
	 */
	@Schema(description = "宠物要求")
	private String petRequirements;

	/**
	 * 培训地点
	 */
	@Schema(description = "培训地点")
	private String trainingLocation;

	/**
	 * 一次训练宠物数量
	 */
	@Schema(description = "一次训练宠物数量")
	private String petsTrainedNumber;



}
