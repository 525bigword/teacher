package com.teacher.study.dao;

import com.teacher.study.dao.provider.CourseWareClassIfySqlProvider;
import com.teacher.study.dao.provider.PowerSqlProvider;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import com.teacher.study.enetity.Power;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseWareClassIfyMapper {
    /**
     * 根据Id修改CourseWareClassIfy
     * @param courseWareClassIfy
     */
    @Update("update courseware_classify set courseware_id=#{coursewareid},classify_id=#{classifyid},update_time=#{updatetime} where id=#{id}")
    void upCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy);
    /**
     * 根据Id删除CourseWareClassIfy
     * @param courseWareClassIfy
     */
    @Delete("delete from courseware_classify where id=#{id}")
    void delCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy);


    /**
     * 新增courseware_classify
     * @param courseWareClassIfy
     */
    @Insert("insert into courseware_classify values(null,#{coursewareid},#{classifyid},#{createtime},#{updatetime})")
    void saveCourseWareClassIfy(CourseWareClassIfy courseWareClassIfy);
    /**修改课件类型*/
    @Update("update courseware_classify set classify_id=#{classifyId},update_time=#{time} where " +
            "CourseWare_id=#{classifyId}")
    void upCourseWareClassIfykj(@Param("CourseWareId") Integer CourseWareId,@Param("classifyId")Integer classifyId,
                               @Param("time")String time);
    /**
     * 查询角色对应的分类
     * @param userid
     * @return
     */
    @Select("select id,user_id,classify_id,create_time,update_time from power where " +
            "user_id=#{userid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
            @Result(column = "classify_id",property = "classIfyModel",
                    one = @One(
                            select = "com.teacher.study.dao.ClassIfyMapper.findClassIfyByIdm",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Power> findCourseWareClassIfyByUserId(@Param("userid")Integer userid);
    /**
     * 对应的分类的课件
     * @param classifyid
     * @return
     */
    @Select("select id,courseware_id,classify_id,create_time,update_time from courseware_classify where " +
            "classify_id=#{classifyid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "courseware_id",property = "courseWare",
                one=@One(
                        select = "com.teacher.study.dao.CourseWareMapper.findCourseWareByIdpage"
                )
            ),

    })
    List<CourseWareClassIfy> findCourseWareClassIfyByClassifyId(@Param("classifyid")Integer classifyid);

    /**
     * 对应的分类的课件并分页用于后台
     * @param classifyid
     * @return
     */
    @Select("select id,courseware_id,classify_id,create_time,update_time from courseware_classify where " +
            "classify_id=#{classifyid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "courseware_id",property = "coursewareid")

    })
    List<CourseWareClassIfy> findCourseWareClassIfyByClassifyIdandnamepage(@Param("classifyid") Integer classifyid);

    @SelectProvider(type = CourseWareClassIfySqlProvider.class,method = "select")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userid"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
            @Result(column = "courseware_id",property = "coursewareid"),
            @Result(column = "classify_id",property = "courseWareClassIfy",
                    one=@One(
                            select = "findCourseWareClassIfyByClassifyIdandnamepage"
                    )
            )

    })
    List<Power> findCourseWareClassIfyByid(@Param("classifyid") List<Integer> classifyid);

    @Select("select id,courseware_id,classify_id,create_time,update_time from courseware_classify where " +
            "classify_id=#{classifyid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "courseware_id",property = "coursewareid")

    })
    List<CourseWareClassIfy> findCourseWareClassIfyByClassify(@Param("classifyid") Integer classifyid);
    @Select("select id,courseware_id,classify_id,create_time,update_time from courseware_classify where " +
            "classify_id=#{classifyid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "courseware_id",property = "coursewareid"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
    })
    List<CourseWareClassIfy> findCoursreClassIfyByid(@Param("classifyid") Integer classifyid);

    @Select("select id from courseware_classify where courseware_id=#{coursewareid} and classify_id=#{classifyid}")
    Integer findCourseWareClassIfyIdByCourseWareIdAndClassIfyId(@Param("coursewareid")Integer coursewareid,@Param(
            "classifyid")Integer classifyid);

    @SelectProvider(type = PowerSqlProvider.class,method = "courseware_classify")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "courseware_id",property = "coursewareid"),
            @Result(column = "classify_id",property = "classifyid"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
    })
    List<CourseWareClassIfy> findCoursreClassIfyByids(@Param("classifyid") List<Integer> classifyid);
}
