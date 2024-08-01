package org.mengchong.mcfw.model.entity.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

import java.sql.Timestamp;


@Data
@Schema(description = "角色实体类")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "描述")
    private String description;

//    @Schema(description = "更新时间")
//    private Timestamp updateTime;

}
