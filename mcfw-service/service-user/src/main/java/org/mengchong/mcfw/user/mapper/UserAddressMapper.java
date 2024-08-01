package org.mengchong.mcfw.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mengchong.mcfw.model.entity.user.UserAddress;

import java.util.List;

@Mapper
public interface UserAddressMapper {

    //1 获取用户地址
    List<UserAddress> findByUserId(Long userId);

    //2 获取地址信息
    UserAddress getById(Long id);
    void updateByid(UserAddress userAddress);

    void save(UserAddress userAddress);

    void removeById(Long id);

}