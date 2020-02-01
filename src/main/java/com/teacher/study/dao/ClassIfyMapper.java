package com.teacher.study.dao;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.Power;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassIfyMapper {
    /**
     * 查对应父级的所有子级
     * @return
     */
    @Select("select id,superio_id,`name`,`explain`,create_time,update_time,`order` from classify where superio_id=#{id}")
    List<ClassIfy> findClassIfyBySuperioId(ClassIfy classIfy);
    /**
     * 获取一级分类
     * @return
     */
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where id=#{id}")
    ClassIfy findClassIfyToZero(@Param("id") Integer id);
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where superio_id=0")
    List<ClassIfyModel> findClassIfyToZeroModel();
    /**
     * 获取一级分类
     * @return
     */

    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where superio_id=0")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "value"),
            @Result(column = "superio_id",property = "superiorid"),
            @Result(column = "name",property = "name"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "order",property = "order"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),

    })
    List<ClassIfyModel> findClassIfyToParent();
    /**
     * 获取同等级最大order
     * @param classIfy
     * @return
     */
    @Select("select max(`order`) from classify where superio_id=#{superiorid} ")
    Integer findOrder(ClassIfy classIfy);

    /**
     * 修改classIfy
     * @param classIfy
     */
    @Update("update classify set `name`=#{name},update_time=#{updatetime} " +
            "where id=#{id}")
    void update(ClassIfy classIfy);
    /**
     * 删除ClassIfy
     * @param classIfy
     */
    @Delete("delete from classify where id=#{id}")
    void delClassIfyById(ClassIfy classIfy);

    /**
     * 查询所有ClassIfy
     * @return
     */
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where superio_id=#{id} " +
            "order by `order`")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "value"),
            @Result(column = "superio_id",property = "superiorid"),
            @Result(column = "name",property = "name"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "order",property = "order"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
            @Result(column = "id",property = "classIfyModels",
                many = @Many(
                        select = "findClassIfyAll",
                        fetchType = FetchType.LAZY
                )
            )

    })
    List<ClassIfyModel> findClassIfyAll(@Param("id")Integer id);
    /**
     * 查询所有下级ClassIfy
     * @return
     */
    @Select("select classify_id from power where " +
            "user_id=#{userid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "classify_id",property = "classIfy",
                one = @One(
                        select = "findClassIfyById",
                        fetchType = FetchType.DEFAULT
                )
            ),
    })
    List<Power> findClassIfyAllToZero(@Param("userid") Integer userid);
    /**
     * 新增classify
     * @param classIfy
     */
    @Insert("insert into classify values(null,#{superiorid},#{name},#{explain},#{order},#{createtime},#{updatetime})")
    void saveClassIfy(ClassIfy classIfy);

    /**
     * 根绝id查ClassIfy
     * @param classIfy
     * @return
     */
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where id=#{id}")
    ClassIfy findClassIfyById(ClassIfy classIfy);

    /**
     * 根绝id查ClassIfy
     * @param classIfy
     * @return
     */
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where id=#{id} and " +
            "superio_id=0")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "superio_id",property = "superiorid"),
            @Result(column = "name",property = "name"),
            @Result(column = "explain",property = "explain"),
            @Result(column = "order",property = "order"),
            @Result(column = "create_time",property = "createtime"),
            @Result(column = "update_time",property = "updatetime"),
            @Result(column = "id",property = "classIfyModels",
                    many = @Many(
                            select = "findClassIfyByIdms",
                            fetchType = FetchType.LAZY
                    )
            )

    })
    ClassIfyModel findClassIfyByIdm(ClassIfy classIfy);
    @Select("select id,superio_id,`name`,`explain`,`order`,create_time,update_time from classify where superio_id=#{superiorid}")
    List<ClassIfyModel>  findClassIfyByIdms(@Param("superiorid") Integer superiorid);


}
