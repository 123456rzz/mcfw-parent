package org.mengchong;

import org.mengchong.mcfw.common.anno.EnableUserLoginAuthInterceptor;
import org.mengchong.mcfw.common.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.mengchong.mcfw"})
@EnableUserLoginAuthInterceptor
@EnableUserTokenFeignInterceptor
@ComponentScan(basePackages = {"org.mengchong.mcfw"})
@EnableCaching //开启缓存注解功能
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }
}