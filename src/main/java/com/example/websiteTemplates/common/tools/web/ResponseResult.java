package com.example.websiteTemplates.common.tools.web;

import com.fasterxml.jackson.annotation.JsonInclude;

//设置为如果加该注解的字段为null,那么就不序列化这个字段了
//也就是为空时也序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 功能成功标识
     */
    private boolean flag;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    //构造函数
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, boolean flag, String msg, T data) {
        this.code = code;
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, boolean flag) {
        this.code = code;
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
