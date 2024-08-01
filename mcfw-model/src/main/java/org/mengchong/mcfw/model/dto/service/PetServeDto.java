package org.mengchong.mcfw.model.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

@Data
@Schema(description = "宠物服务搜索条件实体类")
public class PetServeDto extends BaseEntity {
    @Schema(description = "服务类型")
    private String serviceType;

    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    private String serviceName;

}
