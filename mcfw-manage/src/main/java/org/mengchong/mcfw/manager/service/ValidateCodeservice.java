package org.mengchong.mcfw.manager.service;

import org.mengchong.mcfw.model.vo.system.ValidateCodeVo;

public interface ValidateCodeservice {
    // 生成图片验证码
    ValidateCodeVo generatevalidatecode();
}
