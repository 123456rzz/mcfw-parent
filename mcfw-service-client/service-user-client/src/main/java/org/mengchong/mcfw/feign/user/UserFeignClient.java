package org.mengchong.mcfw.feign.user;


import org.mengchong.mcfw.model.entity.user.UserAddress;
import org.mengchong.mcfw.model.entity.user.UserBrowseHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable("id") Long id) ;

    /**
     * @Description: 远程调用获取浏览量最多的商品
     * @param
     */
    @GetMapping("api/user/userInfo/auth/BrowseHistory")
    public UserBrowseHistory getByBrowseHistory();

}