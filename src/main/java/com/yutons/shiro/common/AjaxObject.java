package com.yutons.shiro.common;

/**
 * @ClassName : AjaxObject  //类名
 * @Description : 返回的值  //描述
 * @Author : peaceWW  //作者
 * @Date: 2020-07-31 11:29  //时间
 */
public class AjaxObject {
    private String code;
    private String message;
    private Object data;
    public AjaxObject(){}
    public AjaxObject(String code){
        this.code = code;
        this.message=ResultCode.SUCCESS.toString();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
