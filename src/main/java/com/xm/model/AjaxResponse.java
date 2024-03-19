package com.xm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjaxResponse {
    private Boolean success;
    private Object payLoad;
    private String message;

    public AjaxResponse(Boolean success, Object payLoad ,String message) {
        this.success = success;
        this.payLoad = payLoad;
        this.message = message;
    }

    public static AjaxResponse ok(Object payLoad){
        return new AjaxResponse(true, payLoad,"操作成功");
    }

    public static AjaxResponse fail(String message){
        return new AjaxResponse(false, null ,message);
    }

    public static AjaxResponse map(Map<Object,Object> map ,int total){
        return new AjaxResponse(true, map, "操作成功");
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}