package org.mengchong.mcfw.model.entity.product;

import org.mengchong.mcfw.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}