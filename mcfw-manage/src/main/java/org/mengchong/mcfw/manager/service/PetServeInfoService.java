package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.service.PetServeDto;
import org.mengchong.mcfw.model.entity.service.PetServeInfo;

public interface PetServeInfoService {
    //1 宠物列表分页
    PageInfo<PetServeInfo> findByPage(Integer page, Integer limit, PetServeDto petServeDto);
    // 2 宠物信息添加
    void save(PetServeInfo petServeInfo);

    // 3 宠物信息修改
    void update(PetServeInfo petServeInfo);
    //    4 宠物信息删除
    void deleteById(Long serveId);
}
