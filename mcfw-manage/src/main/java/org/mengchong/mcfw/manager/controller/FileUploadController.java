package org.mengchong.mcfw.manager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;

import org.mengchong.mcfw.manager.service.FileUploadService;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Tag(name = "用户文件上传（头像）接口")
@RestController
@RequestMapping(value = "/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService ;

    @PostMapping(value = "/fileUpload")
    public Result<String> fileUploadService(@RequestParam(value = "file") MultipartFile multipartFile) {
        //1.获取上传的文件
        String fileUrl = fileUploadService.fileUpload(multipartFile) ;
        return Result.build(fileUrl , ResultCodeEnum.SUCCESS) ;
    }
}
