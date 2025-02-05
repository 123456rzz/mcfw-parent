package org.mengchong.mcfw.user.service;


import org.mengchong.mcfw.model.entity.base.Region;

import java.util.List;

/**
 * @author ljl
 * @create 2023-11-04-20:55
 */
public interface UserRegionService {

    /**
     * @Description: 收货地址省市区显示
     * @param code
     */
    List<Region> selectByParentCode(Integer code);
}
