package com.template.orm.mapper;

import com.template.orm.domain.Tuser;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.List;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
public interface TuserMapper {
    
    List<Tuser> findAll();
}
