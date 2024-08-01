package org.mengchong.mcfw.utils;

import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.entity.user.UserInfo;

/**
 * 工具类，操作ThreadLocal对象,用于保存当前登录的用户对象
 */
public class AuthContextUtil {
    // 创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>() ;

    // 定义存储数据的静态方法 设置当前登录的用户
    public static void set(SysUser sysUser) {
//        System.out.println(sysUser.getUserName());
        threadLocal.set(sysUser);
    }

    // 定义获取数据的方法 获取登录用户
    public static SysUser get() {
        return threadLocal.get() ;
    }

    // 删除数据的方法 清除用户信息
    public static void remove() {
        threadLocal.remove();
    }

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;


    // 定义存储数据的静态方法  设置小程序当前登录的用户
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法 获取小程序当前登录的用户
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法  删除小程序当前登录的用户
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }

}
