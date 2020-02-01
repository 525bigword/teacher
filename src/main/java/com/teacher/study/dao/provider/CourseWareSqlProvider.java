package com.teacher.study.dao.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseWareSqlProvider {
    public String selectNId( String name, List<Integer> classifyid,
                             Integer index, Integer pageNum){
        //select id,`name`,outline,`explain`,img,vido,code,create_time,update_time from courseware_classify where id=#{id}
        StringBuffer sql=new StringBuffer();
        sql.append("select id,`name`,outline,`explain`,img,vido,code,create_time,update_time from courseware where id");
        if(classifyid.size()>1){
        //    sql.append(" where id ="+id+" and name like %"+name+"%");
        }
       // sql.append(" where id ="+id+" and name like %"+name+"%");
        return sql.toString();
    }
}
