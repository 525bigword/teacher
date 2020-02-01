package com.teacher.study.enetity;

import java.io.Serializable;

public class CourseWareClassIfy implements Serializable {
    private Integer id;
    private Integer coursewareid;
    private Integer classifyid;
    private String createtime;
    private String updatetime;
    //业务属性
    private CourseWare courseWare;
    private ClassIfy classIfy;

    public CourseWare getCourseWare() {
        return courseWare;
    }

    public void setCourseWare(CourseWare courseWare) {
        this.courseWare = courseWare;
    }

    public ClassIfy getClassIfy() {
        return classIfy;
    }

    public void setClassIfy(ClassIfy classIfy) {
        this.classIfy = classIfy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoursewareid() {
        return coursewareid;
    }

    public void setCoursewareid(Integer coursewareid) {
        this.coursewareid = coursewareid;
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
