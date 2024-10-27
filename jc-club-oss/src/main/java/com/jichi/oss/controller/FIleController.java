package com.jichi.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jichi.oss.entity.FileInfo;
import com.jichi.oss.service.FileService;
import com.jichi.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhuFan
 * @data 2024/10/9/009 12:42
 */
@RestController
public class FIleController {

    @Resource private MinioUtil minioUtil;
    @Resource private FileService fileService;

    @RequestMapping("/test")
    public String test() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }

}
