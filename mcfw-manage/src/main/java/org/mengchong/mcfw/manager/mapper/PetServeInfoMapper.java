package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.dto.service.PetServeDto;
import org.mengchong.mcfw.model.entity.service.PetServeInfo;

import java.util.List;

@Mapper
public interface PetServeInfoMapper {

    // 1 宠物服务列表分页查询接口
    List<PetServeInfo> findByPage(PetServeDto petServeDto);
    // 2 宠物服务添加
    void save(PetServeInfo petServeInfo);
    // 3 宠物服务修改
    void update(PetServeInfo petServeInfo);
    // 4 宠物服务删除
    void delete(Long serveId);
}
