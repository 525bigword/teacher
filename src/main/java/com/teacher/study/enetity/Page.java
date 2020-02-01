package com.teacher.study.enetity;

import java.io.Serializable;

public class Page implements Serializable {
    private String name;
    private Integer index;
    private Integer pageNum;

    @Override
    public String toString() {
        return "Page{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", pageNum=" + pageNum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
