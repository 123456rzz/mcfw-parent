package org.mengchong.mcfw.model.entity.order;

import org.mengchong.mcfw.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderStatistics extends BaseEntity {

    private Date orderDate; //日期列表
    private BigDecimal totalAmount;//金额列表
    private Integer totalNum;
    
}