package com.jichi.oss.util;

import com.jichi.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Minio文件操作工具
 *
 * @author ZhuFan
 * @data 2024/10/9/009 12:09
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建bucket桶
     */
    public void createBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     */
    public void uploadFile(InputStream inputStream, String bucket, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName).stream(
                inputStream, -1, 5242889L).build());
    }

    /**
     * 列出所有bucket
     */
    public List<String> getAllBucket() throws Exception {
        return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 列出当前bucket及文件
     */
    public List<FileInfo> getAllFile(String bucket) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).build());
        List<FileInfo> fileList = new ArrayList<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(item.objectName());
            fileInfo.setIsDirectory(item.isDir());
            fileInfo.setEtag(item.etag());
            fileList.add(fileInfo);
        }
        return fileList;
    }

    /**
     * 下载文件
     */
    public InputStream downloadFile(String bucket, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * 删除桶
     */
    public void deleteBucket(String bucket) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    /**
     * 删除文件
     */
    public void deleteFile(String bucket, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
    }
}
