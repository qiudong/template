package com.template.api.http.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxHttpResponse implements Serializable {

    private String touser;
    private Content text;
    private String msgtype;

    @Data
    public class Content{
        private String content;
    }
}
