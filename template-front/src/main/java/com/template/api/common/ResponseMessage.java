package com.template.api.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qiudong
 * @date: 2018/8/27
 * @version: V1.0
 * @review: qiudong /2018/8/27
 */
@Data
public class ResponseMessage<T> implements Serializable {

    private Integer code;
    private T date;
    private String message;

    public ResponseMessage(Integer code, T date, String message) {
        this.code = code;
        this.date = date;
        this.message = message;
    }

    public ResponseMessage(Integer code, T date) {
        this.code = code;
        this.date = date;
    }

    public ResponseMessage(Integer code) {
        this.code = code;
    }

    public ResponseMessage(String message) {
        this.code = 500;
        this.message = message;
    }


    public static ResponseMessage ok() {
        return new ResponseMessage(200);
    }

    public static <T> ResponseMessage ok(T data) {
        return new ResponseMessage(200, data);
    }

    public static <T> ResponseMessage ok(String message, T data) {
        return new ResponseMessage(200, data, message);
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(500, message);
    }

    public static ResponseMessage error(Integer code, String message) {
        return new ResponseMessage(code, message);
    }


}
