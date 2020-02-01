package com.teacher.study.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * ClassIfy的增强类
 */
@JsonIgnoreProperties(value = { "handler" })
public class ClassIfyModel implements Serializable {
    private Integer id;
    private Integer value;
    private Integer superiorid;
    private String name;
    private String explain;
    private Integer order;
    private String createtime;
    private String updatetime;
    private ClassIfyModel classIfyModel;
    private List<ClassIfyModel> classIfyModels;

    public ClassIfyModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public ClassIfyModel getClassIfyModel() {
        return classIfyModel;
    }

    public void setClassIfyModel(ClassIfyModel classIfyModel) {
        this.classIfyModel = classIfyModel;
    }

    public List<ClassIfyModel> getClassIfyModels() {
        return classIfyModels;
    }

    public void setClassIfyModels(List<ClassIfyModel> classIfyModels) {
        this.classIfyModels = classIfyModels;
    }
}
