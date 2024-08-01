package org.mengchong.mcfw.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.comment.PetCommentInfo;
import org.mengchong.mcfw.model.entity.comment.ServiceCommontInfo;

import java.util.List;

@Mapper
public interface PetCommentInfoMapper {

    // 1 用户服务评论列表分页查询接口
    List<PetCommentInfo> findByPage();
    // 2 用户服务评论添加
    void save(PetCommentInfo petCommentInfo);
    // 3 用户服务评论修改
    void update(PetCommentInfo petCommentInfo);
    // 4 用户服务评论删除
    void delete(Long commentId);
}
