package com.template.api.demo

import groovy.lang.Closure

import com.template.api.AbstractController
import com.template.orm.mapper.TuserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author: qiudong* @date: 2018/7/23
 * @version: V1.0* @review: qiudong /2018/7/23
 */
@RestController
class GroovyController extends AbstractController {
    @Autowired
    private TuserMapper tuserMapper

    @RequestMapping("/groovy")
    def home() {
        def list = [1, 2] as ArrayList

        list each { println it }
        return list

    }


}
