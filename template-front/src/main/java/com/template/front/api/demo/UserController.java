package com.template.front.api.demo;

import com.template.front.api.AbstractController;
import com.template.front.api.common.ResponseMessage;
import com.template.orm.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@RequestMapping("/api")
@RestController
public class UserController extends AbstractController {
    @Autowired
    private TuserMapper tuserMapper;

    public UserController() {
        System.out.println("111");

    }

    @GetMapping("/login/account")
    public ResponseMessage home(){
        return wrapperSupplier(() -> {
            tuserMapper.findAll();
            return "";
        });
    }

}
