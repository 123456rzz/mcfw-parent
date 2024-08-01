package org.mengchong.mcfw.manager.service;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.entity.comment.PetCommentInfo;

public interface PetCommentInfoService {

    // 1 宠物社区评论列表分页查询接口
    PageInfo<PetCommentInfo> findByPage(Integer page, Integer limit);
    // 2 宠物社区评论添加
    void save(PetCommentInfo petCommentInfo);
    // 3 宠物社区评论修改
    void update(PetCommentInfo petCommentInfo);
    // 4 宠物社区评论删除
    void deleteById(Long commentId);
}
