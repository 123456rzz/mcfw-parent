package org.mengchong;


import org.mengchong.mcfw.common.anno.EnableUserLoginAuthInterceptor;
import org.mengchong.mcfw.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = {"org.mengchong.mcfw"})
@EnableConfigurationProperties(value = { AlipayProperties.class }) // 开启通过实体类读取配置文件内容封装数据功能
@ComponentScan(basePackages = {"org.mengchong.mcfw"})
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}