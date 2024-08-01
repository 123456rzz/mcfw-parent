package org.mengchong.mcfw.manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @author ljl
 * @create 2023-10-29-17:09
 */
@Mapper
public interface ProductUnitMapper {
    public abstract List<ProductUnit> findAll();
}