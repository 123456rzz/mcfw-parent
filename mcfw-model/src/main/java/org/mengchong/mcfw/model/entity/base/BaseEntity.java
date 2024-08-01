package org.mengchong.mcfw.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "唯一标识")
    private Long id;
//    /**
//     * 用户令牌
//     */
//    @Schema(description = "用户令牌")
//    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "是否删除")
    private Integer isDeleted;

}