package com.teacher.study.service.impl;

import com.teacher.study.dao.ClassIfyMapper;
import com.teacher.study.dao.CourseWareClassIfyMapper;
import com.teacher.study.dao.CourseWareMapper;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.enetity.Power;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.service.CourseWareClassIfyService;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class CourseWareClassIfyServiceImpl implements CourseWareClassIfyService {
    @Autowired
    private CourseWareClassIfyMapper courseWareClassIfyMapper;
    @Autowired
    private CourseWareMapper courseWareMapper;
    @Autowired
    private ClassIfyMapper classIfyMapper;

    @Override
    public Integer findCourseWareClassIfyByCountClassifyId(List<Integer> classifyid,String name) {
        Integer courseWareByIdsToCount = courseWareMapper.findCourseWareByIdsToCount(classifyid, name);
        return courseWareByIdsToCount;
    }

    /**
     * 新增课件业务

     * @param courseWareClassIfy
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy)throws Exception {
        try{
            courseWareClassIfyMapper.saveCourseWareClassIfy(courseWareClassIfy);
        }catch (Exception e){
            log.info("com.teacher.study.service.impl.CourseWareClassIfyServiceImpl" +
                    ".saveCourseWareClassIfy方法sql查询出错");
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public Map findCourseWareClassIfyByClassifyId(List<Integer> classifyid, Integer index, Integer pageNum) {
        ClassIfy classIfy=new ClassIfy();
        classIfy.setId(classifyid.get(0));
        List<ClassIfy> classIfyBySuperioId = classIfyMapper.findClassIfyBySuperioId(classIfy);
        List<CourseWareClassIfy> coursreClassIfyByid=null;
        if(classIfyBySuperioId.size()==0){
            coursreClassIfyByid  = courseWareClassIfyMapper.findCoursreClassIfyByid(classifyid.get(0));
        }else{
            List<Integer> list=new ArrayList<>();
            for (ClassIfy ify : classIfyBySuperioId) {
                list.add(ify.getId());
            }
            coursreClassIfyByid= courseWareClassIfyMapper.findCoursreClassIfyByids(list);
        }
        List<Integer> ids=new ArrayList<>();
        for (CourseWareClassIfy courseWareClassIfy : coursreClassIfyByid) {
            ids.add(courseWareClassIfy.getCoursewareid());
        }
        List<CourseWare> courseWareByIdsTopage = courseWareMapper.findCourseWareByIdsTopage(ids, index, pageNum,"");
        Map map=new ConcurrentHashMap();
        map.put("count",findCourseWareClassIfyByCountClassifyId(ids,""));
        map.put("data",courseWareByIdsTopage);
        return map;
    }
}
