package org.mengchong.mcfw.manager.service.impl;


import org.mengchong.mcfw.manager.mapper.ProductUnitMapper;
import org.mengchong.mcfw.manager.service.ProductUnitService;
import org.mengchong.mcfw.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljl
 * @create 2023-10-29-17:08
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper ;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
