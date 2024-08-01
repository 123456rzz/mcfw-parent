package org.mengchong.mcfw.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mengchong.mcfw.common.exception.GuiguException;
import org.mengchong.mcfw.manager.service.SysMenuService;
import org.mengchong.mcfw.manager.service.SysUserService;
import org.mengchong.mcfw.manager.service.ValidateCodeservice;
import org.mengchong.mcfw.model.dto.system.LoginDto;
import org.mengchong.mcfw.model.entity.system.SysUser;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.mengchong.mcfw.model.vo.system.LoginVo;
import org.mengchong.mcfw.model.vo.system.SysMenuVo;
import org.mengchong.mcfw.model.vo.system.ValidateCodeVo;
import org.mengchong.mcfw.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeservice validateCodeservice;

    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 用户登录方法
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) { //得到表单提交过来的数据loginDto
        try {
            // 尝试登录
            //调用services层的login方法，返回一个对象，用户的token
            LoginVo loginVo = sysUserService.login(loginDto);
            // 登录成功，返回成功的结果，接口返回统一的响应信息，data，code，message
            return Result.build(loginVo, ResultCodeEnum.SUCCESS);
        } catch (GuiguException e) {
            // 捕获到登录异常，返回错误的结果
            return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
        }
    }

    /**
     * 用户退出
     * @param token
     * @return
     */
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) { //从请求头获取token第二种方式
        // 1 调用services层的logout方法，传入token，根据token，用redis对象从redis数据库里删除他的用户信息。
       sysUserService.logout(token);
        //2 响应信息返回
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
//    @PostMapping(value = "/getUserInfo")
//    public Result getUserInfo(@RequestHeader(name = "token") String token) {
//        //从请求头获取token第二种方式
//        //1 从请求头获取token 第一种方式
//        //String token = request.getHeader("token");
//        // 2 根据 token查询redis 获取用户信息
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        //3 用户信息返回
//        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
//    }


    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    //从ThreadLocal对象中获取用户信息
    @Operation(summary = "从ThreadLocal对象中获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo() {
//        System.out.println(AuthContextUtil.get());
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS) ; //AuthContextUtil.get()存储到ThreadLocal对象的用户信息，
    }

    /**
     * 生成图片验证码接口方法
     * @return
     */
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generatevalidateCode() {
        //调用services层的方法来生成验证码，返回包含验证码key和value的对象信息
        ValidateCodeVo validateCodevo =validateCodeservice.generatevalidatecode();
        //接口返回统一的响应信息，data，code，message
        return Result.build(validateCodevo,ResultCodeEnum.SUCCESS);
    }

//    /**
//     * 查询用户可以操作菜单,实现动态菜单
//     * @return
//     */
//    @GetMapping(value = "/menus")
//    public Result menus(){
//        List<SysMenuVo> list=sysMenuService.findMenusByUserId();
//        return Result.build(list,ResultCodeEnum.SUCCESS);
//    }

}
