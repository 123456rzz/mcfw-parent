package org.mengchong.mcfw.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器的类
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    //注入redis对象，用来操作redis数据库
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //在目标方法之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 获取请求方式
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {      // 如果是跨域预检请求，直接放行
            return true;
        }

        // 获取请求头的token
        String token = request.getHeader("token");
//        System.out.println(token);
        //如果token为空，响应208状态码给前端：用户未登录
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
//        System.out.println("user:login" + token);
        // 如果token不为空，那么此时验证token的合法性
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login" + token);//根据token从redis查询用户数据
//        System.out.println("通过get(Object key)方法获取set(K key, V value)方法新增的字符串值:" + sysUserInfoJson);
        //如果用户信息数据为空，响应208状态码给前端：用户未登录
        if (StrUtil.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response);
            return false;

        }

        // 将用户数据存储到ThreadLocal中
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        //调用set方法，存储数据
        AuthContextUtil.set(sysUser);

        // 重置Redis中的用户数据的有效时间为30分钟
        redisTemplate.expire("user:login" +  token , 30 , TimeUnit.MINUTES) ;

        // 放行
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    //等方法全部完成后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();  // 移除threadLocal中的数据
    }
}