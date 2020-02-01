package com.teacher.study.dao;

import com.teacher.study.dao.provider.CourseWareClassIfySqlProvider;
import com.teacher.study.dao.provider.CourseWareSqlProvider;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.CourseWare;
import com.teacher.study.enetity.CourseWareClassIfy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseWareMapper {

    @Update("update courseware set code=#{code} where id=#{id}")
    void upCourseWareCodeById(CourseWare courseWare);
    /**
     * 查询所有课件
     * @return
     */
    @Select("select id,`name`,outline,`explain`,img,vido,create_time,update_time from courseware ")
    List<CourseWare> findAllCourseWare();
    /**
     * 根据Id修改CourseWare
     * @param courseWare
     */
    @Update("update courseware set name=#{name},outline=#{outline},explain=#{explain}," +
            "update_time=#{updatetime} where id=#{id}")
    void upCourseWare(CourseWare courseWare);
    /**
     * 新增courseware
     * @param courseWare
     */
    @Insert("insert into courseware values(null,#{name},#{outline},#{explain},#{img},#{vido},#{code},#{createtime},#{updatetime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void saveCourseWare(CourseWare courseWare);

    /**
     * 删除一个courseware
     * @param courseWare
     */
    @Delete("delete from courseware where id=#{id}")
    void delCourseWare(CourseWare courseWare);

    /**
     * 根据id和code查CourseWare
     * @param courseWare
     * @return
     */
    @Select("select id,`name`,outline,`explain`,img,vido,create_time,update_time from courseware where id=#{id} and " +
            "code=#{code}")
    CourseWare findCourseWareByIdAndCode(CourseWare courseWare);
    /**
     * 根据vido和code查CourseWare
     * @param courseWare
     * @return
     */
    @Select("select id,`name`,outline,`explain`,img,vido,create_time,update_time from courseware where id=#{id} and " +
            "vido=#{vido}")
    CourseWare findCourseWareByvidoAndCode(CourseWare courseWare);
    /**
     * 根据id查CourseWare
     * @param courseWare
     * @return
     */
    @Select("select id,`name`,outline,`explain`,img,vido,create_time,update_time from courseware where id=#{id}")
    CourseWare findCourseWareById(CourseWare courseWare);
    /**
     * 查所有CourseWare
     * @return
     */
    @Select("select id,name,outline,explain,img,vido,create_time,update_time from courseware where")
    List<CourseWare> findCourseWareAll();

    /**
     * 根据多个id查CourseWare
     * @param
     * @return
     */

    @Select("select id,`name`,outline,`explain`,img,vido,code,create_time,update_time from courseware where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "outline",property = "outline"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "img",property = "img"),
            @Result(column = "vido",property = "vido"),
            @Result(column = "code",property = "code"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime")
    })
    List<CourseWare> findCourseWareByIdpage(@Param("id") Integer id);
    /**
     * 按分类获取课件
     * @param
     * @return
     */

    @Select("select id,`name`,outline,`explain`,img,vido,code,create_time,update_time from courseware where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "outline",property = "outline"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "img",property = "img"),
            @Result(column = "vido",property = "vido"),
            @Result(column = "code",property = "code"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime")
    })
    List<CourseWare> findCourseWareByClassIfyMapperAndNamepage(@Param("name") String name,@Param("classifyid") Integer classifyid,
                                                               @Param("index") Integer index,@Param("pageNum") Integer pageNum);

    /**查询出该用户下目标视频和对应数据*/
    @Select("SELECT \n" +
            "\t*\n" +
            "FROM\n" +
            "\tcourseware_classify\n" +
            "LEFT JOIN courseware ON courseware_classify.courseware_id = courseware.id\n" +
            "LEFT JOIN power ON courseware_classify.classify_id = power.classify_id\n" +
            "where power.user_id=#{userid} and courseware_classify.courseware_id=#{coursewareid} limit 0,1")
    @Results({
            @Result(column = "classify_id",property = "value"),
            @Result(column = "courseware_id",property = "id")
    })
    ClassIfyModel findCourseWareToclassifyAndCourseWare(@Param("coursewareid") Integer coursewareid, @Param("userid") Integer userid);

    @SelectProvider(type = CourseWareClassIfySqlProvider.class,method = "findCourseWare")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "outline",property = "outline"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "img",property = "img"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
    })
    List<CourseWare> findCourseWareByIdsTopage(@Param("ids") List<Integer> ids,@Param("index") Integer index,
                                               @Param("pageNum") Integer pageNum,@Param("name")String name);

    @SelectProvider(type = CourseWareClassIfySqlProvider.class,method = "findCourseWareCount")
    Integer findCourseWareByIdsToCount(@Param("ids") List<Integer> ids,@Param("name")String name);
}
