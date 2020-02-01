package com.teacher.study.service.impl;

import com.teacher.study.dao.CourseWareClassIfyMapper;
import com.teacher.study.dao.CourseWareMapper;
import com.teacher.study.dao.PowerMapper;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import com.teacher.study.service.CourseWareClassIfyService;
import com.teacher.study.service.PowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private CourseWareMapper courseWareMapper;
    @Autowired
    private CourseWareClassIfyMapper courseWareClassIfyMapper;

    @Override
    public Integer findPowerByIdCount(List<Integer> id,String name) {
        return powerMapper.findPowerByIdCount(id,name);
    }

    @Override
    @Transactional
    public void savePower(Power power) throws Exception {
        try{
            powerMapper.savePower(power);
        }catch (Exception e){
            log.info("com.teacher.study.service.impl.PowerServiceImpl" +
                    "savePower方法sql查询出错");
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public Map<String,List> findPowerByUserIdAndnamepage(Integer classify_id, String name,Integer index,
    Integer pageNum) throws Exception {
        List<Integer> classify=new ArrayList<>();
        classify.add(classify_id);
        List<Power> courseWareClassIfyByid = courseWareClassIfyMapper.findCourseWareClassIfyByid(classify);
        List<Integer> ids=new ArrayList<>();
        for (Power power : courseWareClassIfyByid) {
            for (CourseWareClassIfy courseWareClassIfy : power.getCourseWareClassIfy()) {
                Integer coursewareid = courseWareClassIfy.getCoursewareid();
                ids.add(coursewareid);
            }
            //Integer coursewareid = power.getCourseWareClassIfya().getCoursewareid();
        }
        List<CourseWare> courseWareByIdsTopage = courseWareMapper.findCourseWareByIdsTopage(ids, index, pageNum, name);
        Map map=new ConcurrentHashMap();
        map.put("ids",ids);
        map.put("data",courseWareByIdsTopage);
        return map;
    }
}
