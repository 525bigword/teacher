package com.teacher.study.util;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.teacher.study.dao.CourseWareMapper;
import com.teacher.study.enetity.CourseWare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class CourseWareJob {
    @Autowired
    private CourseWareMapper courseWareMapper;

    /**
     * @Author Smith
     * @Description 设置天凌晨2点修改所有课件密码
     * @Date 14:23 2019/1/24
     * @Param
     * @return void
     **/
    @Scheduled(cron = "59 59 23 * * ?")
    private void process(){
        List<CourseWare> courseWareAll = courseWareMapper.findCourseWareAll();
        for (CourseWare courseWare : courseWareAll) {
            courseWare.setCode(CodeUtil.createCode());
            courseWareMapper.upCourseWareCodeById(courseWare);
        }
        log.info("@Scheduled(cron = \"59 59 23 * * ?\") 当前设定已触发条件修改所有课件密码");
    }
}
