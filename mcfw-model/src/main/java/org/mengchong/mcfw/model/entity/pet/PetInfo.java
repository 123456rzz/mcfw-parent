package org.mengchong.mcfw.model.entity.pet;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;


/**
 * @Description  
 * @Author  Hunter
 * @Date 2024-03-21 
 */


@Data
@Schema(description = "宠物信息实体类")
public class PetInfo extends BaseEntity {
//	宠物id
//	private static final long serialVersionUID =  2789272190517539643L;
	/**
	 * 宠物主人id
	 */
	@Schema(description = "宠物主人id")
	private Long petOwnerId;

	/**
	 * 宠物名称
	 */
	@Schema(description = "宠物名称")
	private String petName;


	@Schema(description = "宠物照片")
	private String avatarPhoto;

	/**
	 * 宠物性别 （0男 1女 2其他）
	 */
	@Schema(description = "宠物性别 （0男 1女 2其他）")
	private Long petGender;

	/**
	 * 宠物类型（1狗、2猫、3兔子、4猪、5鸟、6蛇、7乌龟、8其他）
	 */
	@Schema(description = "宠物类型")
	private String petType;

	/**
	 * 重量（0  1-5㎏、1 5-10㎏、2 10-20㎏、3 20-40㎏、4 40+㎏）
	 */
	@Schema(description = "重量")
	private String weight;

	/**
	 * 宠物品种
	 */
	@Schema(description = "宠物品种")
	private String petBreed;

	/**
	 * 宠物生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(description = "宠物生日")
	private Date petBirthday;

	/**
	 * 其他资讯
	 */
	@Schema(description = "其他资讯")
	private String otherInformation;

	/**
	 * 兽医姓名
	 */
	@Schema(description = "兽医姓名")
	private String vetName;

//	/**
//	 * 创建时间
//	 */
//   	@Column(name = "create_time" )
//	private Date createTime;
//

//
//	/**
//	 * 更新时间
//	 */
//   	@Column(name = "update_time" )
//	private Date updateTime;


}
