package com.xm.model;

public class AjaxResponse {
    private Boolean success;
    private Object payLoad;

    public AjaxResponse(Boolean success, Object payLoad) {
        this.success = success;
        this.payLoad = payLoad;
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
}