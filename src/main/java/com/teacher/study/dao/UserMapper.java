package com.teacher.study.dao;

import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**模糊查询所有*/
    @Select("select id,`name`,phone,note,create_time,update_time from `user` where `name` like CONCAT('%',#{name},'%') limit #{index}," +
            "#{pageNum}")
    List<User> findUserByLike(@Param("name")String name,@Param("index") Integer index,@Param("pageNum")Integer pageNum);
    /**模糊查询所有*/
    @Select("select id,`name`,phone,note,create_time,update_time from `user` limit #{index}," +
            "#{pageNum}")
    List<User> findUserByLi(@Param("index") Integer index,@Param("pageNum")Integer pageNum);
    /**模糊查询所有数量*/
    @Select("select count(*) from `user` where `name` like CONCAT('%',#{name},'%') ")
    Integer findUserCountlike(@Param("name")String name);
    /**模糊查询所有数量*/
    @Select("select count(*) from `user`")
    Integer findUserCount();
    /**新增user*/
    @Insert("insert into user values(null,#{acc},#{pwd},#{name},#{phone},#{note},#{createtime},#{updatetime})")
    @Options(useGeneratedKeys=true)
    void saveUser(User user);

    /**登录业务*/
    @Select("select id,name,phone,note,create_time,update_time from user where acc=#{acc} and pwd=#{pwd}")
    User findUserByAccAndPwd(User user);

    /**修改账号信息*/
    @Update("update user set acc=#{acc},pwd=#{pwd},name=#{name},phone=#{phone},note=#{note} where id=#{id}")
    void upUserById(User user);
    /**删除用户*/
    @Delete("delete from user where id=#{id}")
    void delUserById(User user);
}
