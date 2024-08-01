package org.mengchong.mcfw.model.vo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liurui
 * @description: 公共返回类
 * @date 2023/4/17 14:01
 */
@Data
@Schema(description = "接口返回对象")
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "处理消息")
    private String message;

    @Schema(description = "业务状态码")
    private Integer code;

    @Schema(description = "数据对象")
    private T data;

    public BaseResult(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    /**
     * 返回成功
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> ok(String message,T data){
        return new BaseResult<>(ECode.OK,message,data);
    }

    /**
     * 返回成功
     * @param message
     * @return
     */
    public static BaseResult ok(String message){
        return new BaseResult<>(ECode.OK,message);
    }

    public static <T> BaseResult<T> ok(T data){
        return new BaseResult<T>(ECode.OK, data);
    }

    /**
     * 返回失败
     * @param message
     * @return
     */
    public static BaseResult fail(String message){
        return new BaseResult<>(ECode.FAIL,message);
    }

    /**
     * 返回失败
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> fail(String message,T data){
        return new BaseResult<>(ECode.FAIL,message,data);
    }

    /**
     * 返回失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> fail(Integer code, String message){
        return new BaseResult<>(code,message);
    }

    /**
     * 返回失败
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> fail(Integer code, T data){
        return new BaseResult<>(code, data);
    }

}
