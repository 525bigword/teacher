package com.teacher.study.controller;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.service.CourseWareClassIfyService;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("coursewareclassify")
public class CourseWareClassIfyController {
    @Autowired
    private CourseWareClassIfyService courseWareClassIfyService;



    @PostMapping("/save")
    public Return save(CourseWareClassIfy courseWareClassIfy){
        if(null==courseWareClassIfy.getClassifyid()||null==courseWareClassIfy.getCoursewareid()){
            return new Return().custom("分类或课件不能为空");
        }else{
            try {
                courseWareClassIfy.setUpdatetime(DateUtil.getTime());
                courseWareClassIfy.setCreatetime(DateUtil.getTime());
                courseWareClassIfyService.saveCourseWareClassIfy(courseWareClassIfy);
                return new Return().yes("");
            } catch (Exception e) {
                return new Return().no();
            }
        }
    }
}
