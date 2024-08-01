package org.mengchong.mcfw.model.entity.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

@Data
@Schema(description = "系统用户实体类")
public class SysUser extends BaseEntity {
//	private static final long serialVersionUID = 1L;
	@Schema(description = "用户名")
	private String userName;

	@Schema(description = "姓名")
	private String name;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "宠物ID")
	private Integer petId;

	@Schema(description = "头像照片")
	private String avatarPhoto;

	@Schema(description = "电子邮件")
	private String email;

	@Schema(description = "手机")
	private String phone;

	@Schema(description = "其他通讯方式")
	private String otherCommunicationMethods;

	@Schema(description = "身份验证")
	private String identifyVerification;

	@Schema(description = "证书 （0培训师证书、1美容证书、2兽医证书")
	private String certificates;

	@Schema(description = "介绍自己")
	private String introduceYourself;

	@Schema(description = "拥有的宠物类型和体验")
	private String typesExperience;

	@Schema(description = "技能和资格")
	private String skillsQualifications;

	@Schema(description = "其他特殊技能")
	private String otherSpecialSkills;

	@Schema(description = "状态（0：正常 1：停用）")
	private Integer status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}