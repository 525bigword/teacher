package com.teacher.study.service;

import com.teacher.study.enetity.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseWareService {

    //根据id查询CourseWare
    CourseWare findCourseWareById(CourseWare courseWare);

    //修改课件业务
    void UpCourseWareClassIfy(CourseWare courseWare)throws Exception;
    //新增CourseWare
    void saveCourseWare(CourseWare courseWare,Integer classifyid)throws Exception;

    //查询所有CourseWare
    List<Power> findCourseWareAll(Integer id)throws Exception;

    //查询CourseWare
    CourseWare findCourseWareByIdAndCode(CourseWare courseWare)throws Exception;
    //查询CourseWare
    CourseWare findCourseWareByvodiAndCode(CourseWare courseWare)throws Exception;
    //删除一个CourseWare
    void delCourseWareClassIfy(Integer coursewareid, Integer userid)throws Exception;
}
