package com.teacher.study.service.impl;

import com.teacher.study.dao.CourseWareClassIfyMapper;
import com.teacher.study.dao.PowerMapper;
import com.teacher.study.dao.UserMapper;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import com.teacher.study.service.CourseWareClassIfyService;
import com.teacher.study.service.CourseWareService;
import com.teacher.study.service.UserService;
import com.teacher.study.util.Base;
import com.teacher.study.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.List;

/**
 * 用户业务逻辑类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private CourseWareService courseWareService;

    @Override
    public Integer findUserCount(String name) {
        Integer userCount=0;
        if("".equals(name)){
            userCount=UserMapper.findUserCount();
        }else{
            userCount = UserMapper.findUserCountlike(name);
        }
        return userCount;
    }

    @Override
    public List<User> findUserBynames(String name, Integer index, Integer pageNum,Integer id) throws Exception {
        if("".equals(name)){
            return   UserMapper.findUserByLi(index*pageNum, pageNum, id);
        }else{
            return  UserMapper.findUserByLike(name, index*pageNum, pageNum);
        }
    }

    /**
     * 新增用户
     * @param user
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveUser(User user,List<Integer> classifyid) throws Exception {
        try{
            /*SqlSession sqlSession = sqlSessionTemplate.openSession();*/
            //sqlSession.
            UserMapper.saveUser(user);
            Power power=new Power();
            power.setUserid(user.getId());
            for (Integer integer : classifyid) {
                power.setClassifyid(integer);
                powerMapper.savePower(power);
            }

        }catch (Exception e){
            log.info("com.teacher.study.service.impl.UserServiceImpl.saveUser方法sql查询出错");
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * 登录业务
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception {
        try{
            User userByAccAndPwd = UserMapper.findUserByAccAndPwd(user);
            return userByAccAndPwd;
        }catch (Exception e){
            log.info("com.teacher.study.service.impl.UserServiceImpl.login方法sql查询出错可能因为没有此账号故无视");
            throw new Exception();
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @throws Exception
     */
    @Transactional
    @Override
    public void upUserById(User user ,List<Integer> classify_id) throws Exception {
        try{
            if(StringUtils.isEmpty(user.getPwd())){
                UserMapper.upUserById3(user);
            }else{
                user.setPwd(Base.encode(user.getPwd()));
                UserMapper.upUserById(user);
            }
            powerMapper.delPowerByUserId(user.getId());
            Power power=new Power();
            power.setUserid(user.getId());
            power.setCreatetime(DateUtil.getTime());
            power.setUpdatetime(DateUtil.getTime());
            for (Integer i : classify_id) {
                power.setClassifyid(i);
                powerMapper.savePower(power);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
    @Transactional
    @Override
    public void delUserById(User user) throws Exception {
        //courseWareService.delCourseWareClassIfy();
        List<Integer> powerByUserId = powerMapper.findPowerByUserId(user.getId());
        for (Integer i : powerByUserId) {
            powerMapper.delPowerById(i);
        }
        UserMapper.delUserById(user);
    }

    @Override
    public Integer findUserByacc(String acc) throws Exception {
        Integer userByAccCount = UserMapper.findUserByAccCount(Base.encode(acc));
        return userByAccCount;
    }

    @Override
    public void upUserById(User user) throws Exception {
        if(StringUtils.isEmpty(user.getPwd())){
            UserMapper.upUserById2(user);
        }else{
            user.setPwd(Base.encode(user.getPwd()));
            UserMapper.upUserByIda(user);
        }

    }
}
