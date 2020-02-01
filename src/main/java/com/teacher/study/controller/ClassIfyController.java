package com.teacher.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.User;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.Return;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("classify")
@Slf4j
public class ClassIfyController {
    @Autowired
    private ClassIfyService classIfyService;


    /**
     * 列表
     *
     * @return
     */
    @PostMapping("/class_list")
    public Return getAllNoZero(@RequestBody JSONObject jsonObject) {
        Integer userid = jsonObject.getInteger("userid");
        try {
            List<Map<String, Object>> courseWareAllNoZero = classIfyService.findCourseWareAllNoZero(userid);
            return new Return().yes(courseWareAllNoZero);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }



    /**
     * 课件列表
     * @return
     */
    @PostMapping("/list")
    public Return getAll(){
        try {
            List<ClassIfyModel> courseWareAll = classIfyService.findCourseWareAll();
            return new Return().yes(courseWareAll);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }

    @PostMapping("/modify")
    public Return update(@RequestBody ClassIfy classIfy){
        if(null==classIfy.getId()){
            return new Return().no();
        }
        try {
            classIfyService.upClassiIfy(classIfy);
            return new Return().yes("");
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }
    @PostMapping("/del")
    public Return del(@RequestBody ClassIfy classIfy){
        if(null==classIfy.getId()){
            return new Return().no();
        }else{
            try {
                classIfyService.delClassiIfy(classIfy);
                return new Return().yes("");
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
        }
    }



    @PostMapping(value ="/get")
    public Return get() throws Exception {
        try {
        List<ClassIfyModel> classiIfyAll = classIfyService.getClassiIfyAll();
        System.out.println(classiIfyAll);
            return new Return().yes(classiIfyAll);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }

    @PostMapping("/add")
    public Return save(@RequestBody  ClassIfy classIfy){
        if(null==classIfy.getSuperiorid()||"".equals(classIfy.getName())){
            return new Return().custom("父级分类或名称不能为空");
        }else{
            try {
                classIfy.setCreatetime(DateUtil.getTime());
                classIfy.setUpdatetime(DateUtil.getTime());
                classIfyService.saveClassiIfy(classIfy);
                return new Return().yes("");
            } catch (Exception e) {
                return  new Return().yes("");
            }
        }
    }
}
