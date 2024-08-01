package org.mengchong.mcfw.manager.config;


import org.mengchong.mcfw.manager.interceptor.LoginAuthInterceptor;
import org.mengchong.mcfw.manager.properties.UserAuthProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *添加一个配置类配置跨域请求
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    //注入拦截器对象
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;
    @Autowired
    private UserAuthProperties userAuthProperties ;		// 注入实体类对象，获取application-dev.xml配置哪个路径不拦截
    //拦截器注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor) //加入拦截器对象
//        .excludePathPatterns("/admin/system/index/login",
//                "/admin/system/index/generateValidateCode") //哪些路径不需要拦截，登录接口和验证码校验接口
//                .addPathPatterns("/**"); //哪些路径需要拦截
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    /**
     * 跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")// 添加路径规则
            .allowCredentials(true)   // 是否允许在跨域的情况下传递Cookie
            .allowedOriginPatterns("*")    // 允许请求来源的域规则
            .allowedMethods("*")
            .allowedHeaders("*");     // 允许所有的请求头
    }

}