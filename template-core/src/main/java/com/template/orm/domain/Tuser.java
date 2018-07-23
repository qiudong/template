package com.template.orm.domain;

import lombok.Data;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@Data
public class Tuser {

    private Integer uId;

    private String userName;

    private String password;

    private String email;

    private String remarks;

}
