package org.mengchong.mcfw.manager.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取application-dev.yml 配置的minio
 */
@Data
@ConfigurationProperties(prefix = "mcfw.minio")
public class MinioProperties {

    private String endpointUrl;//文件在minio URL路径
    private String accessKey;//登录用户名
    private String secreKey; //登录密码
    private String bucketName; //bucket名称

}
