package org.mengchong.mcfw.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mengchong.mcfw.model.entity.user.UserInfo;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    void save(UserInfo userInfo);

    UserInfo getByUsername(@Param("username") String username);

    //1  查询所有用户
    List<UserInfo> findAll();
}