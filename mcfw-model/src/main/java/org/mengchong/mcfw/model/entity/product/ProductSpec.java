package org.mengchong.mcfw.model.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mengchong.mcfw.model.entity.base.BaseEntity;

@Data
@Schema(description = "商品规格实体类")
public class ProductSpec extends BaseEntity {

	@Schema(description = "规格名称")
	private String specName;   // 规格名称

	@Schema(description = "规格值")
	private String specValue;  // 规格值

}