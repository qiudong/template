package com.template.api.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-04-01 11:40
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-01 11:40
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    private String name;
    private Integer age;
}
