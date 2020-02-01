package com.teacher.study.dao.provider;

import com.teacher.study.enetity.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PowerSqlProvider {
    public String a(List<Integer> ids,String name){
        StringBuffer sql=new StringBuffer("select count(*) from courseware where id");
        if(ids.size()>1){
            sql.append(" in(");
            for (int i = 0; i < ids.size(); i++) {
                sql.append(ids.get(i)+",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }else{
            sql.append(" ="+ids.get(0));
        }
        sql.append(" and name like '%"+name+"%'");
        return sql.toString();
    }


    public String courseware_classify(List<Integer> ids){
        StringBuffer sql=new StringBuffer("select id,courseware_id,classify_id,create_time,update_time from " +
                "courseware_classify where classify_id in (");
        for (int i = 0; i < ids.size(); i++) {
            sql.append(ids.get(i)+",");
        }
        sql = sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    public String select(Integer id){
        SQL sql=new SQL();
        sql.SELECT("(SELECT courseware_id FROM courseware_classify WHERE courseware_classify.classify_id = power" +
                ".classify_id ) classify");
        sql.FROM("power");
        sql.WHERE("user_id=#{id}");
        return sql.toString();
    }
}
