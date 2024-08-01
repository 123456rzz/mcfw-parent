package org.mengchong;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mengchong.mcfw.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableKnife4j
@EnableScheduling  //开启 Spring Task定时任务功能
@ComponentScan(basePackages = {"org.mengchong.mcfw"})
@EnableCaching //开启缓存注解功能
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = "org.mengchong.mcfw")
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}