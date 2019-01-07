package com.template.api.demo;

import com.template.api.AbstractController;
import com.template.api.common.ResponseMessage;
import com.template.orm.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@RestController
public class UserController extends AbstractController {
    @Autowired
    private TuserMapper tuserMapper;

    @RequestMapping("/")
    public String home(){
//        return wrapperSupplier(() -> {
//            return tuserMapper.findAll();
//        });
        return tuserMapper.findAll().toString();

    }

}
