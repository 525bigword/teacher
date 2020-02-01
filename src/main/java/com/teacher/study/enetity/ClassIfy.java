package com.teacher.study.enetity;

import java.io.Serializable;

public class ClassIfy implements Serializable {
    private Integer id;
    private Integer superiorid;
    private String name;
    private String explain;
    private Integer order;
    private String createtime;
    private String updatetime;

    @Override
    public String toString() {
        return "ClassIfy{" +
                "id=" + id +
                ", superiorid=" + superiorid +
                ", name='" + name + '\'' +
                ", explain='" + explain + '\'' +
                ", order=" + order +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(Integer superiorid) {
        this.superiorid = superiorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
