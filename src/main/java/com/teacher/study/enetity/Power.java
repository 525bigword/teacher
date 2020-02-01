package com.teacher.study.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(value = { "handler" })
public class Power implements Serializable {
    private Integer id;
    private Integer userid;
    private Integer classifyid;
    private String createtime;
    private String updatetime;

    //业务属性
    private List<CourseWareClassIfy> courseWareClassIfy;
    private CourseWareClassIfy courseWareClassIfya;
    private ClassIfy classIfy;
    private ClassIfyModel classIfyModel;
    public ClassIfy getClassIfy() {
        return classIfy;
    }

    public CourseWareClassIfy getCourseWareClassIfya() {
        return courseWareClassIfya;
    }

    public void setCourseWareClassIfya(CourseWareClassIfy courseWareClassIfya) {
        this.courseWareClassIfya = courseWareClassIfya;
    }

    public ClassIfyModel getClassIfyModel() {
        return classIfyModel;
    }

    public void setClassIfyModel(ClassIfyModel classIfyModel) {
        this.classIfyModel = classIfyModel;
    }

    public void setClassIfy(ClassIfy classIfy) {
        this.classIfy = classIfy;
    }

    public List<CourseWareClassIfy> getCourseWareClassIfy() {
        return courseWareClassIfy;
    }

    public void setCourseWareClassIfy(List<CourseWareClassIfy> courseWareClassIfy) {
        this.courseWareClassIfy = courseWareClassIfy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Integer classifyid) {
        this.classifyid = classifyid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
