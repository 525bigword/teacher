package com.teacher.study.dao;

import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**模糊查询所有*/
    @Select("select id,`name`,acc,pwd,phone,note,create_time,update_time from `user` where `name` like CONCAT('%'," +
            "#{name},'%') limit #{index}," +
            "#{pageNum}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "acc",property = "acc"),
            @Result(column = "pwd",property = "pwd"),
            @Result(column = "name",property = "name"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "note",property = "note"),
            @Result(column = "create_time",property = "createtime")
    })
    List<User> findUserByLike(@Param("name")String name,@Param("index") Integer index,@Param("pageNum")Integer pageNum);
    /**模糊查询所有*/
    @Select("select id,`name`,acc,pwd,phone,note,create_time,update_time from `user` where id !=#{id} limit #{index}," +
            "#{pageNum}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "acc",property = "acc"),
            @Result(column = "pwd",property = "pwd"),
            @Result(column = "name",property = "name"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "note",property = "note"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime")
    })
    List<User> findUserByLi(@Param("index") Integer index,@Param("pageNum")Integer pageNum,@Param("id")Integer id);

    @Select("select count(*) from user where acc =#{name} ")
    Integer findUserByAccCount(@Param("name") String name);

    /**模糊查询所有数量*/
    @Select("select count(*) from `user` where `name` like CONCAT('%',#{name},'%') ")
    Integer findUserCountlike(@Param("name")String name);
    /**模糊查询所有数量*/
    @Select("select count(*) from `user`")
    Integer findUserCount();
    /**新增user*/
    @Insert("insert into user values(null,#{acc},#{pwd},#{name},#{phone},#{note},#{createtime},#{updatetime})")
    @Options(useGeneratedKeys=true,keyColumn = "id",keyProperty = "id")
    void saveUser(User user);

    /**登录业务*/
    @Select("select id,acc,pwd,name,phone,note,create_time,update_time from user where acc=#{acc} and pwd=#{pwd}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "acc",property = "acc"),
            @Result(column = "pwd",property = "pwd"),
            @Result(column = "name",property = "name"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "note",property = "note"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime")
    })
    User findUserByAccAndPwd(User user);

    /**修改账号信息*/
    @Update("update user set acc=#{acc},pwd=#{pwd},name=#{name},phone=#{phone},note=#{note} where id=#{id}")
    void upUserById(User user);
    /**修改账号信息*/
    @Update("update user set acc=#{acc},name=#{name},phone=#{phone},note=#{note} where id=#{id}")
    void upUserById2(User user);
    /**修改账号部分*/
    @Update("update user set pwd=#{pwd},name=#{name},phone=#{phone} where id=#{id}")
    void upUserByIda(User user);
    /**删除用户*/
    @Delete("delete from user where id=#{id}")
    void delUserById(User user);
}
