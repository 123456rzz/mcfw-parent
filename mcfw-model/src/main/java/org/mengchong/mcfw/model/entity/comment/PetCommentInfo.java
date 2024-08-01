package org.mengchong.mcfw.model.entity.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-23 
 */

@Data
@Schema(description = "社区评论信息实体类")
public class PetCommentInfo  extends BaseEntity {
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
	 * 分享内容
	 */
	@Schema(description = "分享内容")
	private String sharedContent;

	/**
	 * 评论时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "评论时间")
	private Date commentTime;





}
