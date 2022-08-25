package com.blog.myblogsystem.utils;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.exception.DbException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiniuUtils {
    public static String url = null;

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.accessSecretKey}")
    private String accessSecretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.url}")
    public void setUrl(String url) {
        QiniuUtils.url = url;
    }

    public boolean upload(MultipartFile file, String fileName){
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            return true;
        } catch (Exception e) {
            throw new DbException(e.getMessage(), CodeConstants.CODE_200);
        }
    }
}