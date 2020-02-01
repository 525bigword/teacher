package com.teacher.study.dao.provider;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class CourseWareClassIfySqlProvider {
    public String findCourseWares(Integer ids,Integer index,Integer pageNum,String name){
        StringBuffer sql=new StringBuffer("select id,`name`,outline,`explain`,img,vido,code,create_time,update_time " +
                "from courseware where id ");
            sql.append(" ="+ids);
        if(!"".equals(name)){
            sql.append(" and name like '%"+name+"%'");
        }
        sql.append(" limit "+index+","+pageNum);
        System.out.println(sql.toString());
        return  sql.toString();
    }





    public String findCourseWareCount(List<Integer> ids,String name){
        StringBuffer sql=new StringBuffer("select count(*) " +
                "from courseware where id ");
        if(ids.size()>1){
            sql.append(" in (");
            for (Integer i : ids) {
                sql.append(i+",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }else{
            sql.append(" ="+ids.get(0));
        }
        if(!"".equals(name)){
            sql.append(" and name like '%"+name+"%'");
        }
        System.out.println(sql.toString());
        return  sql.toString();
    }
    public String findCourseWare(List<Integer> ids,Integer index,Integer pageNum,String name){
        StringBuffer sql=new StringBuffer("select id,`name`,outline,`explain`,img,vido,code,create_time,update_time " +
                "from courseware where id ");
        if(ids.size()>1){
            sql.append(" in (");
            for (Integer i : ids) {
                sql.append(i+",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }else{
            sql.append(" ="+ids.get(0));
        }
        if(!"".equals(name)){
            sql.append(" and（name like '%"+name+"%'");
            sql.append(" and outline like '%"+name+"%'");
            sql.append(" and explain like '%"+name+"%')");
        }
        sql.append(" limit "+index*pageNum+","+pageNum);
        System.out.println(sql.toString());
        return  sql.toString();
    }
    public String select(List<Integer> classifyid){
        /*select id,user_id,classify_id,create_time,update_time from power where " +
        "user_id=#{classifyid} limit #{index},#{pageNum}"*/
        StringBuffer sql=new StringBuffer("select id,user_id,classify_id,create_time,update_time from power where user_id");
        if(classifyid.size()>1){
            sql.append(" in (");
            for (Integer i : classifyid) {
                sql.append(i+",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }else{
            sql.append(" ="+classifyid.get(0));
        }
        System.out.println(sql.toString());
        return  sql.toString();
    }
}
