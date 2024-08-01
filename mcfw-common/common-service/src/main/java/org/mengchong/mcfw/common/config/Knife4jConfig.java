package org.mengchong.mcfw.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置前端H5接口
 */
@Configuration
//@EnableOpenApi//该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
@EnableKnife4j//该注解是knife4j提供的增强注解,Ui提供了例如动态参数、参数过滤、接口排序等增强功能,如果你想使用这些增强功能就必须加该注解，否则可以不用加
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi userApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("user-api")         // 分组名称
                .pathsToMatch("/api/**")  // 接口请求路径规则
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("admin-api")         // 分组名称
                .pathsToMatch("/admin/**")  // 接口请求路径规则
                .build();
    }

    /***
     * @description 自定义接口信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        //
        return new OpenAPI().info(new Info()
                .title("萌宠服务系统API接口文档")
                .version("1.0")
                .description("萌宠服务系统API接口文档")
                .contact(new Contact().name("李佳乐"))); // 设定作者
    }

    /**
     *  配置前端H5接口
     * @return
     */
    @Bean
    public GroupedOpenApi webApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("web-api")         // 分组名称
                .pathsToMatch("/api/**")  // 接口请求路径规则
                .build();
    }
}
