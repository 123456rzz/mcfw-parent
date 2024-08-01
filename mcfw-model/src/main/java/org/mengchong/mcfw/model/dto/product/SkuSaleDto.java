package org.mengchong.mcfw.model.dto.product;

import lombok.Data;

/**
 *  远程调用传输的数据的实体类,更新商品销量
 */
@Data
public class SkuSaleDto {

	private Long skuId;
	private Integer num;

}

