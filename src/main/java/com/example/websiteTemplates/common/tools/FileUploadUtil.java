package com.example.websiteTemplates.common.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    //图片文件上传目录
    public static final String UPLOAD_DIR_PICTURE = "/UploadDir/picture/";
    //视频文件上传目录
    public static final String UPLOAD_DIR_VIDEO = "/UploadDir/video/";

    //获取文件名后缀
    public static String getFileNameSuffix(MultipartFile file) {
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
    }

    public static String getCurrentDir() {
        try {
            File directory = new File("");
            return directory.getCanonicalPath();
        } catch (IOException e) {
            return null;
        }
    }

}
