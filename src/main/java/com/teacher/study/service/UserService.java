package com.teacher.study.service;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.User;

import java.util.List;

/**
 * 用户逻辑接口
 */
public interface UserService {
    //查询数量
    Integer findUserCount(String name);
    //模糊查询name
    List<User> findUserBynames(String name,Integer index,Integer pageNum)throws Exception;
    //新增User
    void saveUser(User user,List<Integer> classifyid)throws Exception;
    //登录业务
    User login(User user)throws Exception;
    //修改用户信息
    void upUserById(User user, List<Integer> classify_id)throws Exception;
    //删除用户User
    void delUserById(User user)throws Exception;
}
