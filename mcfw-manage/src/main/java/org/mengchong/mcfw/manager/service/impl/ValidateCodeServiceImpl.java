package org.mengchong.mcfw.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.mengchong.mcfw.manager.service.ValidateCodeservice;
import org.mengchong.mcfw.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeservice {

    //注入redis对象,通过它操作redis数据库
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 生成图片验证码
    @Override
    public ValidateCodeVo generatevalidatecode() {

        // 1 通过工具生成图片验证码 hutool
        //验证码：宽，高、干扰线、干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        //2 把验证码存储到redis里面，设置redis的key：uuid redis的value：验证码值，设置过期时间
        String codeValue = circleCaptcha.getCode();//4位验证码
        String imageBase64 = circleCaptcha.getImageBase64();//返回图片验证码，base64编码
        //设置redis的key：uuid redis的value：验证码值
        //设置过期时间,5分钟有效

        String codeKey = UUID.randomUUID().toString().replace("_",""); // 生成uuid作为图片验证码的key
        // 将验证码存储到Redis中 设置过期时间,5分钟有效
       redisTemplate.opsForValue().set("user:validate" + codeKey,codeValue,5, TimeUnit.MINUTES);
        //  3 返回ValidateVo对象
       // 建响应结果数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);//redis存储数据key
        //因为最终页面要显示图片，
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        //返回数据
        return validateCodeVo;

    }
}
