package org.mengchong.mcfw.model.dto.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 宠物信息列表查询条件
 */
@Data
@Schema(description = "请求参数实体类")
public class PetInfoDto {

    @Schema(description = "宠物名称")
    private String petName;

    /**
     * 宠物类型（1狗、2猫、3兔子、4猪、5鸟、6蛇、7乌龟、8其他）
     */
    @Schema(description = "宠物类型")
    private String petType;
}
