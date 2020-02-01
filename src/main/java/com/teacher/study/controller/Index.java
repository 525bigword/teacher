package com.teacher.study.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.base.Utf8;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.Power;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.service.CourseWareClassIfyService;
import com.teacher.study.service.CourseWareService;
import com.teacher.study.util.Base64;
import com.teacher.study.util.Return;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.LineIterator;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@Slf4j
@RequestMapping("index")
public class Index {
    @Autowired
    private ClassIfyService classIfyService;
    @Autowired
    private CourseWareService courseWareService;
    @Autowired
    private CourseWareClassIfyService courseWareClassIfyService;
    @RequestMapping(value ="/first_classify_list",method = RequestMethod.POST)
    public Return getZero(Integer id){
        try {
            List<ClassIfy> classiIfyAll = classIfyService.getClassiIfyToZero(id);
            return new Return().yes(classiIfyAll);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }
    /**
     * 课件列表
     * @return
     */
    @PostMapping("/classify_list")//Integer classifyid
    public Return getAll(Integer classifyid){
        //Integer classifyid = json.getInteger("classifyid");
        try {
            List<ClassIfyModel> courseWareTochild = classIfyService.findCourseWareTochild(classifyid);
            return new Return().yes(courseWareTochild);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }
    /**
     * 课件列表****************
     * @return
     */
    @PostMapping("/query")
    public Map findCoursewareClassifyByClassifyId( Integer classify_id,
                                   Integer index,Integer pageNum                   ){
        //	"classify_id":"[1,2,3,4,5,6]"
//        JSONArray classify = json.getJSONArray("classify_id");
//        List<Integer> classify_id = JSONObject.parseArray(classify.toJSONString(), Integer.class);
//        Integer index = json.getInteger("index");
//        Integer pageNum = json.getInteger("pageNum");

        if(null==index||index<0){
            index=0;
        }
        if(null==pageNum||pageNum<0){
            pageNum=6;
        }
        List<Integer> integers = Arrays.asList(classify_id);
        Map courseWareClassIfyByClassifyId = courseWareClassIfyService.findCourseWareClassIfyByClassifyId(integers, index, pageNum);
        Map map=new ConcurrentHashMap();
        Object count = courseWareClassIfyByClassifyId.get("count");
        DecimalFormat df=new DecimalFormat("0.00");
        Double totalPages=Double.valueOf((int)count%pageNum == 0?((int)count/pageNum):((int)count/pageNum)+1);
        map.put("count",count);
        map.put("data",courseWareClassIfyByClassifyId.get("data"));
        map.put("totalPages",(int)Math.ceil(totalPages));
        return map;
    }

    @GetMapping("/details")
    public Return getVideos(CourseWare courseWare,HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        try {
            //获取地址视频
            CourseWare courseWareByIdAndCode = courseWareService.findCourseWareByIdAndCode(courseWare);
            FileInputStream fis = null;
            OutputStream os = null;
            System.out.println(courseWareByIdAndCode.getVido());
            System.out.println(courseWareByIdAndCode.getVido().substring(20));
            fis = new FileInputStream(courseWareByIdAndCode.getVido().substring(20));
            String vidoSuffix = courseWareByIdAndCode.getVido().substring(courseWareByIdAndCode.getVido().lastIndexOf(".") + 1);
            response.setContentType("video/"+vidoSuffix); // 设置返回的文件类型
            os = response.getOutputStream();
            int size = fis.available(); // 得到文件大小
            System.out.println(size);
            for (int i = 0; i < 50; i++) {
                byte data[] = new byte[size/50];
                fis.read(data); // 读数据
                os.write(data);
                os.flush();
            }

            System.out.println(vidoSuffix);
            fis.close();
            fis = null;
            os.close();
            os = null;
            return new Return().yes("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Return().custom("没有此视频文件");
        } catch (IOException e) {
            e.printStackTrace();
            return new Return().custom("文件解析异常");
        }catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }
    @GetMapping("/a")
    public String a(){
        System.out.println("aaa");
        return Index.class.getResource("/").toString();
    }
}
