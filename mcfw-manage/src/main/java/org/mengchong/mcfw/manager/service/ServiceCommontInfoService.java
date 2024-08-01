package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.comment.ServiceCommontInfo;

public interface ServiceCommontInfoService {

    // 1 用户服务评论列表分页查询接口
    PageInfo<ServiceCommontInfo> findByPage(Integer page, Integer limit);
    // 2 用户服务评论添加
    void save(ServiceCommontInfo serviceCommontInfo);
    // 3 用户服务评论修改
    void update(ServiceCommontInfo serviceCommontInfo);
    // 4 用户服务评论删除
    void deleteById(Long commentId);
}
