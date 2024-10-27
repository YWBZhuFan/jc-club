package com.jichi.oss.entity;

import lombok.Data;

/**
 * 文件类
 * @author ZhuFan
 * @data 2024/10/9/009 12:32
 */
@Data
public class FileInfo {

    private String fileName;

    private Boolean isDirectory;

    private String etag;
}
