package org.mengchong.mcfw.user.service;


import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.model.dto.h5.UserLoginDto;
import org.mengchong.mcfw.model.dto.h5.UserRegisterDto;
import org.mengchong.mcfw.model.entity.user.UserBrowseHistory;
import org.mengchong.mcfw.model.entity.user.UserCollect;
import org.mengchong.mcfw.model.entity.user.UserInfo;
import org.mengchong.mcfw.model.vo.h5.UserInfoVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 业务接口
public interface UserInfoService {

    // 1 用户注册
    void register(UserRegisterDto userRegisterDto);
    // 2 用户登录
    Object login(UserLoginDto userLoginDto);
    // 3  获取当前登录用户信息
    UserInfoVo getCurrentUserInfo(String token);

    /**
     * @Description: 新增浏览商品
     * @param id
     */
    void saveUserCollect(Long id);

    //浏览商品分页展示
    PageInfo<UserCollect> findUserBrowseHistoryPage(Integer page, Integer limit);

    //收藏商品分页展示
    PageInfo<UserBrowseHistory> findUserCollectPage(Integer page, Integer limit);

    //取消收藏
    void updatecancelCollect(Long skuId);

    //商品收藏
    void savecollect(Long skuId);

    //查看用户是否收藏
    Boolean findUserisCollect(Long skuId);

//    远程调用：获取浏览量最多的商品
    UserBrowseHistory getMostFrequentSkuId();

//    查询所有用户
    List<UserInfo> findAll();
}