package com.teacher.study.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 前端控制器返回的状态
 */
public class Return implements Serializable {
    private Integer code;
    private Object msg;
    private Object data;
    private Object da;
    private Return(Integer code,Object msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public Return(){}
    /**
     * 返回失败自定义状态
     * @param
     * @return
     */
    public Return custom(String msg){
        return new Return(2001,msg,"");
    }
    /**
     * 返回失败状态
     * @param
     * @return
     */
    public Return no(){
        return new Return(2001,"失败","");
    }
    /**
     * 返回成功状态
     * @param data
     * @return
     */
    public Return yes(Object data){
        return new Return(2000,"成功",data);
    }
    public Return yes(Object data,Object da){
        return new Return(2000,data,da);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDa() {
        return da;
    }

    public void setDa(Object da) {
        this.da = da;
    }
}
