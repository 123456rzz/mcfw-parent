package org.mengchong.mcfw.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * @author ljl
 * @create 2023-10-25-13:19
 * 读取application-dev.xml配置的路径拦截
 */

@Data
@ConfigurationProperties(prefix = "mcfw.auth")      // 前缀不能使用驼峰命名
public class UserAuthProperties {

    private List<String> noAuthUrls ; //新建集合获取application-dev.xml配置

}