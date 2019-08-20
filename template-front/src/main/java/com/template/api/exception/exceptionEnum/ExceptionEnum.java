package com.template.api.exception.exceptionEnum;


/**
 * @author: qiudong
 * @date: 2018/8/28
 * @version: V1.0
 * @review: qiudong /2018/8/28
 */
public enum ExceptionEnum {

    SYSTEM_EXCEPTION(0000, "系统异常"),
    BUSINESS_EXCEPTION(0001, "业务异常");


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
