package org.mengchong;


import org.mengchong.mcfw.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.mengchong.mcfw"})
@EnableFeignClients(basePackages = {"org.mengchong.mcfw"})
@EnableUserLoginAuthInterceptor  //拦截前端所有以api开头的接口，只是把当前用户直接放到ThreadLocal中即可，没有别的业务
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 排除数据库的自动化配置，Cart微服务不需要访问数据库
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class , args) ;
    }

}