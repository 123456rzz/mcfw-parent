package org.mengchong.mcfw.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String fileUpload(MultipartFile multipartFile);

    String upload(MultipartFile file);
}
