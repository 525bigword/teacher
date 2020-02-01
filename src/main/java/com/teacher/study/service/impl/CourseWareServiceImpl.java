package com.teacher.study.service.impl;

import com.teacher.study.controller.Index;
import com.teacher.study.dao.ClassIfyMapper;
import com.teacher.study.dao.CourseWareClassIfyMapper;
import com.teacher.study.dao.CourseWareMapper;
import com.teacher.study.dao.PowerMapper;
import com.teacher.study.enetity.*;
import com.teacher.study.service.CourseWareService;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class CourseWareServiceImpl implements CourseWareService {

    @Autowired
    private CourseWareMapper courseWareMapper;
    @Autowired
    private CourseWareClassIfyMapper courseWareClassIfyMapper;
    @Autowired
    private ClassIfyMapper classIfyMapper;
    @Autowired
    private PowerMapper powerMapper;

    @Override
    public CourseWare findCourseWareById(CourseWare courseWare) {
        return courseWareMapper.findCourseWareById(courseWare);
    }

    /**
     * 修改课件业务
     * @throws Exception
     */
    @Transactional
    @Override
    public void UpCourseWareClassIfy(CourseWare courseWare) throws Exception {
        //获取当前项目
        courseWare.setUpdatetime(DateUtil.getTime());
        courseWareMapper.upCourseWare(courseWare);
    }
    /**
     * 删除课件业务
     * @throws Exception
     */
    @Transactional
    @Override
    public void delCourseWareClassIfy(Integer coursewareid,Integer userid) throws Exception {

        ClassIfyModel courseWareToclassifyAndCourseWare = courseWareMapper.findCourseWareToclassifyAndCourseWare(coursewareid, userid);
        CourseWare courseWare=new CourseWare();
        courseWare.setId(courseWareToclassifyAndCourseWare.getId());
        CourseWare courseWareById = courseWareMapper.findCourseWareById(courseWare);
        courseWareMapper.delCourseWare(courseWare);
        //删除对应分类
        CourseWareClassIfy courseWareClassIfy=new CourseWareClassIfy();
        courseWareClassIfy.setId(courseWareToclassifyAndCourseWare.getValue());
        Integer coursewareclassifyid =
                courseWareClassIfyMapper.findCourseWareClassIfyIdByCourseWareIdAndClassIfyId(courseWareToclassifyAndCourseWare.getId(), courseWareToclassifyAndCourseWare.getValue());
        CourseWareClassIfy courseWareClassIfy1=new CourseWareClassIfy();
        courseWareClassIfy1.setId(coursewareclassifyid);
        courseWareClassIfyMapper.delCourseWareClassIfy(courseWareClassIfy1);
        //删除文件
        String uri=Index.class.getResource("/").toString();
        String url=uri.substring(0, uri.indexOf("/",uri.indexOf("/")+1 ));
        System.out.println(courseWareById.getImg());
        System.out.println(courseWareById.getImg().substring(20));
        FtpUtil.delFile(courseWareById.getImg().substring(20));
        FtpUtil.delFile(courseWareById.getVido().substring(20));
    }
    /**
     * 新增文件
     * @param courseWare
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveCourseWare(CourseWare courseWare,Integer classifyid) throws Exception {
        try{
            courseWareMapper.saveCourseWare(courseWare);
            CourseWareClassIfy courseWareClassIfy=new CourseWareClassIfy();
            courseWareClassIfy.setClassifyid(classifyid);
            courseWareClassIfy.setCreatetime(DateUtil.getTime());
            courseWareClassIfy.setUpdatetime(DateUtil.getTime());
            courseWareClassIfy.setCoursewareid(courseWare.getId());
            courseWareClassIfyMapper.saveCourseWareClassIfy(courseWareClassIfy);
        }catch (Exception e){
            log.info("com.teacher.study.service.impl.CourseWareClassIfyServiceImpl" +
                    ".saveCourseWare方法sql查询出错");
            e.printStackTrace();
            throw new Exception();
        }
    }


    @Override
    public List<Power> findCourseWareAll(Integer id) throws Exception {
        List<Power> courseWareClassIfyByClassifyId = courseWareClassIfyMapper.findCourseWareClassIfyByUserId(id);


        return courseWareClassIfyByClassifyId;
    }


    /**
     * 视频验证码验证业务
     * @param courseWare
     * @return
     * @throws Exception
     */
    @Override
    public CourseWare findCourseWareByIdAndCode(CourseWare courseWare) throws Exception {

        return courseWareMapper.findCourseWareByIdAndCode(courseWare);
    }

    @Override
    public CourseWare findCourseWareByvodiAndCode(CourseWare courseWare) throws Exception {
        CourseWare courseWareByIdAndCode = courseWareMapper.findCourseWareByIdAndCode(courseWare);
        return courseWareByIdAndCode;
    }
}
