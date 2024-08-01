package org.mengchong.mcfw.manager;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mengchong.mcfw.common.log.annotation.EnableLogAspect;
import org.mengchong.mcfw.manager.properties.MinioProperties;
import org.mengchong.mcfw.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableKnife4j
@SpringBootApplication
@EnableScheduling  //开启 Spring Task定时任务功能
@EnableLogAspect  //操作日志记录
@EnableAsync //通过异步线程执行saveSysOperLog方法
@ComponentScan(basePackages = {"org.mengchong.mcfw"})
@EnableConfigurationProperties(value={UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {

    public static void main(String[] args) {
       SpringApplication.run(ManagerApplication.class,args);

    }
}