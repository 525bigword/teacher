package com.teacher.study.service;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.enetity.Power;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CourseWareClassIfyService {

    Integer findCourseWareClassIfyByCountClassifyId(List<Integer> classifyid,String name);
    //删除CourseWareClassIfy
    //void delCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy)throws Exception;
    //新增CourseWareClassIfy
    void saveCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy)throws Exception;
    Map findCourseWareClassIfyByClassifyId(List<Integer> classifyid, Integer index, Integer pageNum);
}
