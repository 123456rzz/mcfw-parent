package org.mengchong.mcfw.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.base.Region;

import java.util.List;

/**
 * @author ljl
 * @create 2023-11-04-21:04
 */
@Mapper
public interface UserRegionMapper {

    List<Region> selectByParentCode(Integer code);

    String selectByCode(String Code);
}
