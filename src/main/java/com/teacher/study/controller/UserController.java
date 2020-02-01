package com.teacher.study.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teacher.study.enetity.Page;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import com.teacher.study.service.PowerService;
import com.teacher.study.service.UserService;
import com.teacher.study.util.Base64;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.JwtUtil;
import com.teacher.study.util.Return;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("getme")
    public Return getMe(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token=authorization.substring(7);
        Claims claims = JwtUtil.parseJWT(token);
        return new Return().yes(claims.get("user"));
    }
    @PostMapping("/query")
    public Return query(@RequestBody JSONObject json){
        Integer index = json.getInteger("index");
        Integer pageNum = json.getInteger("pageNum");
        String name = json.getString("name");
        List<User> userBynames = null;
        try {
            index=index-1;
            if(index==null||index<=0){
                index=0;
            }
            if(pageNum==null||pageNum<0){
                pageNum=6;
            }
            Integer userCount = userService.findUserCount(name);
            userBynames = userService.findUserBynames(name, index, pageNum);
            Map map=new ConcurrentHashMap();
            Double totalPages=Double.valueOf((userCount/pageNum));
            map.put("data",userBynames);
            map.put("count",userCount);
            map.put("totalPages",Math.ceil(totalPages)<=0?1:(int)Math.ceil(totalPages));
            return new Return().yes(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Return().no();
        }
    }

    @PostMapping("/del")
    public Return del(@RequestBody User user){
        if(null!=user.getId()){
            try {
                userService.delUserById(user);
                return new Return().yes("");
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
        }
        return new Return().no();
    }
    @PostMapping("/setuser")
    public Return setUser(@RequestBody JSONObject json){
        Integer id = json.getInteger("id");
        String acc = json.getString("acc");
        String pwd = json.getString("pwd");
        String name = json.getString("name");
        String phone = json.getString("phone");
        JSONArray classify = json.getJSONArray("classify_id");
        List<Integer> classify_id = JSONObject.parseArray(classify.toJSONString(), Integer.class);
        String note = json.getString("note");
            User user=new User();
            user.setId(id);
            user.setPhone(phone);
            user.setAcc(acc);
            user.setPwd(pwd);
            user.setName(name);
            try {
                userService.upUserById(user,classify_id);
                return new Return().yes("");
            } catch (Exception e) {
                e.printStackTrace();
                return new Return().no();
            }
    }

    @PostMapping("/login")
    public Return login(@RequestBody User user){
        if("".equals(user.getAcc())||"".equals(user.getPwd())){
            log.info("账号或密码不能为空");
            return new Return().custom("账号或密码不能为空");
        }else{
            log.info(user.toString());
            user.setAcc(Base64.encode(user.getAcc()));
            user.setPwd(Base64.encode(user.getPwd()));
            try {
                User login = userService.login(user);
                if(null==login.getId()){
                    return new Return().custom("没有此账号");
                }
                String token = JwtUtil.createJwt(login);
                Map map=new ConcurrentHashMap();
                return new Return().yes(token,login);
            } catch (Exception e) {
                return new Return().no();
            }
        }
    }

    @PostMapping("/add")//
    public Return save(@RequestBody JSONObject json){
        String acc = json.getString("acc");
        String pwd = json.getString("pwd");
        String name = json.getString("name");
        String phone = json.getString("phone");
        JSONArray classify = json.getJSONArray("classify_id");
        List<Integer> classify_id = JSONObject.parseArray(classify.toJSONString(), Integer.class);
        String note = json.getString("note");
        if("".equals(acc)||"".equals(pwd)||"".equals(name)){
            return new Return().custom("请确认数据填写完整");
        }else{
            try {
                User user =new User();
                user.setAcc(Base64.encode(acc));
                user.setPwd(Base64.encode(pwd));
                user.setPhone(phone);
                user.setName(name);
                user.setNote(note);
                user.setUpdatetime(DateUtil.getTime());
                user.setCreatetime(DateUtil.getTime());
                userService.saveUser(user,classify_id);
                return new Return().yes("");
            } catch (Exception e) {
                return new Return().no();
            }
        }
    }
}
