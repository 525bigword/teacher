package com.teacher.study.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
@Slf4j
@Component
public class FtpUtil {
    public static void delFile(String fileName){
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
    public static void writeFile(MultipartFile multipartFile,String path,String fileName) throws FileNotFoundException {
        File dir = new File(path);
        //判断目录是否存在，不存在则创建目录
       /* File a = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!a.exists()){
            a.mkdirs();
        }*/
        if (!dir.exists()){
            dir.mkdirs();
        }
        //生成文件
        File vidofile = new File(dir, fileName);

        //传输内容
        try {
            multipartFile.transferTo(vidofile);
            System.out.println(File.pathSeparator);
        } catch (IOException e) {
            log.info("com.teacher.study.service.impl.CourseWareClassIfyServiceImpl.saveCourseWare文件上传失败");
            e.printStackTrace();
        }
    }
}
