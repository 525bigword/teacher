package com.teacher.study.dao;

import com.teacher.study.dao.provider.PowerSqlProvider;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerMapper {
    /**
     * 新增power
     * @param power
     */
    @Insert("insert into power values(null,#{userid},#{classifyid},#{createtime},#{updatetime})")
    void savePower(Power power);
    /**
     *  查询当前登录用户的视频
     * @return
     */
    @Select("select id,user_id,classify_id,create_time,update_time from power where user_id=#{id}")
    //@SelectProvider(type = PowerSqlProvider.class,method = "select")
    List<Integer> findPowerAll(@Param("id") Integer id);
    /**根据id查询数量*/
    @SelectProvider(type = PowerSqlProvider.class,method = "a")
    Integer findPowerByIdCount(@Param("ids") List<Integer> ids,@Param("name")String name);
    /**根据id查询数量*/
    @Select("select id from power where user_id=#{id} ")
    List<Integer> findPowerByUserId(@Param("id") Integer id);
    /**根据userid查询classify_id*/
    @Select("select classify_id from power where user_id=#{id} ")
    List<Integer> findPowerToclassify_idByUserId(@Param("id") Integer id);
    /**根据userid查询classify_id*/
    @Select("select classify_id from power where user_id=#{id} ")
    @Results({
            @Result(column = "classify_id",property = "classIfy",
                one = @One(select = "com.teacher.study.dao.ClassIfyMapper.findClassIfyById")
            ),
    })
    List<Power> findPowerToclassify_idByUserIda(@Param("id") Integer id);
    /**根据id查询classifyId*/
    @Select("select id from power where user_id=#{id} ")
    List<Integer> findclassifyIdById(@Param("id") Integer id);
    @Select("select classify_id from power where user_id=#{id} ")
    List<Integer> findPowerToClassIfyIdByUserId(@Param("id") Integer id);
    @Delete("delete from power where id=#{id}")
    void delPowerById(@Param("id")Integer id);
    @Delete("delete from power where user_id=#{id}")
    void delPowerByUserId(@Param("id")Integer id);
}
