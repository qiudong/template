package com.template.orm.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@Data
public class Tuser implements Serializable {

    private Integer uId;

    private String userName;

    private String password;

    private String email;

    private String remarks;

}
