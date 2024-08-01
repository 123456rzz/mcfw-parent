package org.mengchong.mcfw.model.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "宠物服务订单搜索条件实体类")
public class PetOrderDto {

    @Schema(description = "订单号")
    private String orderNumber;

    @Schema(description = "订单状态")
    private String orderStatus;

}
