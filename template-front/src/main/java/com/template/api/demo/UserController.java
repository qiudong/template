package com.template.api.demo;

import com.template.api.AbstractController;
import com.template.orm.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String home(@RequestParam("aaa") String aaa){
//        return wrapperSupplier(() -> {
//            return tuserMapper.findAll();
//        });
        List<String> arrayList = new ArrayList<>();
        return "1212121";

    }

    /***
     *
     * 1. 初始化映射容器
     *
     * 2. 执行前置拦截器
     *
     * 3. 执行函数 （参数绑定）
     *
     * 4. 返回页面
     *
     * 5. 执行后置拦截器
     *
     * 6. 执行最终的拦截器
     *
     *
     */

    // HandlerMapping  HandlerMethod 拦截器[]

    // HandlerAdapter ---> Method 执行

    @PostMapping("/test")
    public String test(@PathVariable Map<String,String> map){
        return "11";
    }

}
