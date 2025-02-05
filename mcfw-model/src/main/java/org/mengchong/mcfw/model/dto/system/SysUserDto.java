package org.mengchong.mcfw.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 *  查询条件
 */
@Data
@Schema(description = "请求参数实体类")
public class SysUserDto {

    @Schema(description = "搜索关键字")
    private String keyword ;

    @Schema(description = "开始时间")
    private String createTimeBegin ;

    @Schema(description = "结束时间")
    private String createTimeEnd;

}
