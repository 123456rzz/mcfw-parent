package org.mengchong.mcfw.model.entity.comment;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-22 
 */

@Data
@Schema(description = "用户服务评论信息实体类")
public class ServiceCommontInfo extends BaseEntity {

	/**
	 * 用户id
	 */
	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "服务请求id")
	private Long serviceRequestId;

	/**
	 * 评论星级
	 */
	@Schema(description = "评论星级")
	private Integer reviewStar;

	/**
	 * 描述
	 */
	@Schema(description = "描述")
	private String description;





}
