package com.teacher.study.service;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.Power;

import java.util.List;
import java.util.Map;

public interface ClassIfyService {
    //新增classify
    void saveClassiIfy(ClassIfy classIfy) throws Exception;
    //修改classify
    void upClassiIfy(ClassIfy classIfy) throws Exception;
    //删除classify
    void delClassiIfy(ClassIfy classIfy) throws Exception;
    //查询所有classify
    List<ClassIfyModel> getClassiIfyAll() throws Exception;
    //查询最高级分类classify
    List<ClassIfy> getClassiIfyToZero(Integer id) throws Exception;
    //查询所有CourseWare
    List<ClassIfyModel> findCourseWareAll()throws Exception;
    //查询所有CourseWare
    List<Map<String,Object>> findCourseWareAllNoZero(Integer userid)throws Exception;
    //查询用户所有CourseWare
    List<ClassIfyModel> findCourseWareByUser(Integer user_id)throws Exception;
    List<ClassIfyModel> findCourseWareTochild(Integer ClassIf);
}
