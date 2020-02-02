package com.teacher.study.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teacher.study.dao.CourseWareMapper;
import com.teacher.study.enetity.*;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.service.CourseWareService;
import com.teacher.study.service.PowerService;
import com.teacher.study.util.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@RestController
@RequestMapping("courseware")
public class CourseWareController {
    @Autowired
    private CourseWareService courseWareService;
    @Autowired
    private PowerService powerService;


    /**
     * 修改
     * @return
     */
    @PostMapping("/modify")
    public Return update(@RequestBody CourseWare courseWare){
        if(null==courseWare.getId()||"".equals(courseWare.getName())||"".equals(courseWare.getOutline())||"".equals(courseWare.getExplain())||"".equals(courseWare.getCode())){
            return new Return().custom("数据不完整");
        }else{
            try {
                courseWare.setName(courseWare.getName().replace(" ",""));
                CourseWare courseWareById = courseWareService.findCourseWareById(courseWare);
                if(!courseWareById.getVido().equals(courseWare.getVido())){
                    FtpUtil.delFile(courseWareById.getVido().substring(20));
                }
                if(!courseWareById.getImg().equals(courseWare.getImg())){
                    FtpUtil.delFile(courseWareById.getImg().substring(20));
                }
                courseWareService.UpCourseWareClassIfy(courseWare);
                return new Return().yes("");
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
        }
    }

    /**
     * 管理账号使用
     * @return
     */
    @PostMapping("/queryAll")
    public Return findPowerAll(@RequestBody JSONObject json){
        Integer index=json.getInteger("index");
        Integer pageNum=json.getInteger("pageNum");
        String name=json.getString("name");
        name=name.replace(" ","");
        try {
            index=index-1;
            if(index==null||index<0){
                index=0;
            }
            if(pageNum==null||pageNum<0){
                pageNum=6;
            }
            Map courseWareAll = courseWareService.findCourseWareAll(index, pageNum, name);
            Map<String,Object> map=new ConcurrentHashMap<>();
            Double totalPages=Double.valueOf((int)courseWareAll.get("count")%pageNum==0?
                    ((int)courseWareAll.get("count")/pageNum):
                    ((int)courseWareAll.get("count")/pageNum)+1);
            map.put("data",courseWareAll.get("data"));
            map.put("count",courseWareAll.get("count"));
            map.put("totalPages",(int)Math.ceil(totalPages));
            return new Return().yes(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }

    /**
     * 后台获取对应用户的课件
     * @return
     *//*@RequestParam(value = "name",required = false) String name,
    @RequestParam(value = "classify_id",required = false) Integer classify_id,
    @RequestParam(value = "index",required = false)Integer index,
    @RequestParam(value = "pageNum",required = false) Integer pageNum*/
    @PostMapping("/query")
    public Return findPowerByUser(@RequestBody JSONObject json){
        Integer index=json.getInteger("index");
        Integer pageNum=json.getInteger("pageNum");
        String name=json.getString("name");
        Integer userid=json.getInteger("userid");
        name=name.replace(" ","");
            try {
                index=index-1;
                if(index==null||index<0){
                    index=0;
                }
                if(pageNum==null||pageNum<0){
                    pageNum=6;
                }

                Map<String,List> powerByUserIdAndnamepage = powerService.findPowerByUserIdAndnamepage(userid, name,
                        index, pageNum);
                Integer powerByIdCount = powerService.findPowerByIdCount(powerByUserIdAndnamepage.get("ids"),name);
                Map<String,Object> map=new ConcurrentHashMap<>();
                Double totalPages=Double.valueOf(powerByIdCount%pageNum==0?(powerByIdCount/pageNum):
                        (powerByIdCount/pageNum)+1);
                map.put("data",powerByUserIdAndnamepage.get("data"));
                map.put("count",powerByIdCount);
                map.put("totalPages",(int)Math.ceil(totalPages));
                return new Return().yes(map);
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
    }


    @PostMapping("/classify_list")
    public Return getAll(@RequestBody JSONObject json){
        try {
            List<Power> powers = courseWareService.findCourseWareAll(json.getInteger("userid"));
            return new Return().yes(powers);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }

    @PostMapping("/dellist")
    public ResponseEntity<String> delList(@RequestBody JSONObject json){
        JSONArray idjsons = json.getJSONArray("ids");
        List<Integer> ids = JSONObject.parseArray(idjsons.toJSONString(), Integer.class);
        if(ids.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            try {
                for (int i = 0; i < ids.size(); i++) {
                    courseWareService.delCourseWareClassIfy(ids.get(i));
                }
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
    }

    @PostMapping("/del")//@RequestParam("coursewareid") Integer coursewareid,@RequestParam("userid") Integer userid
    public Return del(@RequestBody JSONObject json){
        Integer coursewareid = json.getInteger("coursewareid");

        if(null==coursewareid){
            return new Return().no();
        }else{
            try {
                courseWareService.delCourseWareClassIfy(coursewareid);
                return new Return().yes("");
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
        }
    }

    /**
     * 删除文件
     */
    @PostMapping("/delfile")
    public void delFile(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
    /**
     * 文件上传接口
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    @PostMapping("/file")
    public Object uploadfile(@RequestParam(name = "file") MultipartFile file,HttpServletRequest request) throws FileNotFoundException {
        String uri=Index.class.getResource("/").toString();
        String url=uri.substring(0, uri.indexOf("/",uri.indexOf("/")+1 ));
        //生成新文件名，防止文件名重复而导致文件覆盖
        //1、获取原文件后缀名
        String vidoFileName = file.getOriginalFilename();
        String vidoSuffix = vidoFileName.substring(vidoFileName.lastIndexOf('.'));
        //1.2图片
        //2、使用UUID生成新文件名
        //2.1视频
        String vidoNewFileName = UUID.randomUUID() + vidoSuffix;
        if(Pattern.compile("(mp4|flv|avi|rm|rmvb|wmv|ogg)").matcher(vidoSuffix).find()){
            FtpUtil.writeFile(file,url.substring(9)+"/vido/",vidoNewFileName);
            Map map=new ConcurrentHashMap();
            map.put("url",
                    "http://123.56.42.31/vido/"+vidoNewFileName);
            return map;
        }else{
            FtpUtil.writeFile(file,url.substring(9)+"/img/",
                    vidoNewFileName);
        }
        Map map=new ConcurrentHashMap();
        map.put("url",
                "http://123.56.42.31/img/"+vidoNewFileName);
        return  map;
    }

    /**
     * 新增课件
     * @return
     */
    @PostMapping("/add")// CourseWare courseWare,@RequestParam Integer classify_id
    public Return save(@RequestBody JSONObject json){
        CourseWare courseWare=new CourseWare();
        courseWare.setCode(CodeUtil.createCode());
        courseWare.setImg(json.getString("img"));
        courseWare.setVido(json.getString("vido"));
        courseWare.setOutline(json.getString("outline"));
        courseWare.setName(json.getString("name"));
        courseWare.setExplain(json.getString("explain"));
        Integer classify_id=json.getInteger("classify_id");
        if("".equals(courseWare.getName())||"".equals(courseWare.getCode())){
            return new Return().custom("请确认数据无误");
        }else if(null==courseWare.getVido()||null==courseWare.getImg()){
            return new Return().custom("无法解析文件");
        }else{
            try {
                courseWare.setCreatetime(DateUtil.getTime());
                courseWare.setUpdatetime(DateUtil.getTime());
                courseWare.setCode(courseWare.getCode());
                courseWareService.saveCourseWare(courseWare,classify_id);
                return new Return().yes("");
            } catch (IOException e) {
                return new Return().custom("解析文件失败");
            }catch (Exception e) {
                return new Return().no();
            }
        }
    }
}
