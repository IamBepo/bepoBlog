package com.blog.myblogsystem.utils;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.exception.DbException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UploadImageUtils {
    static QiniuUtils qiniuUtils ;
    private static String catalogue;

    @Autowired
    public void setQiniuUtils(QiniuUtils qiniuUtils) {
        UploadImageUtils.qiniuUtils = qiniuUtils;
    }

    @Value("${qiniu.catalogue}")
    public void setCatalogue(String catalogue) {
        UploadImageUtils.catalogue = catalogue;
    }

    public static List<String> upload(List<MultipartFile> file,Boolean orEmpty) {
        List<String> result = new ArrayList<>();

        if(!orEmpty){
            for(MultipartFile item : file){
                String originalFilename = item.getOriginalFilename();

                String fileName = catalogue +  UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
                boolean upload = qiniuUtils.upload(item, fileName);
                if (upload){
                    result.add(QiniuUtils.url + fileName);
                }
            }
        }

        if(result.isEmpty()){
            throw new DbException("上传图片失败", CodeConstants.CODE_500);
        }
        return result;
    }

}
