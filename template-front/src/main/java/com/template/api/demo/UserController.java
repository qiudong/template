package com.template.api.demo;

import com.template.api.AbstractController;
import com.template.api.common.HttpClient;
import com.template.api.common.ResponseMessage;
import com.template.orm.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @GetMapping
    public String home(HttpServletRequest request, HttpServletResponse response) {
//        return wrapperSupplier(() -> {
//            return tuserMapper.findAll();
//        });
        System.out.println("ssss");
        String echostr = request.getParameter("echostr");//随机字符串

//        PrintWriter pw = null;
//        try {
//            pw = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pw.print(echostr);
//        pw.flush();
//        pw.close();
        return echostr;

    }

    @PostMapping
    public void post(HttpServletRequest request, HttpServletResponse response){
        System.out.println("post");
    }

}
