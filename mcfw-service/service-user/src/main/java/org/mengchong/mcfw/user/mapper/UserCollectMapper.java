package org.mengchong.mcfw.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mengchong.mcfw.model.entity.user.UserBrowseHistory;
import org.mengchong.mcfw.model.entity.user.UserCollect;

import java.util.List;

/**
 * @author ljl
 * @create 2023-11-11-21:23
 */

@Mapper
public interface UserCollectMapper {
    void saveUserCollect(UserCollect userCollect);

    List<UserCollect> findUserBrowseHistoryPage(Long id);

    List<UserBrowseHistory> findUserCollectPage(Long id);
}
