package com.template.front.api.beanDemo;

import org.springframework.stereotype.Component;

@Component
public class BaseBean {

    public BaseBean() {
        System.out.println("初始化BaseBean");
    }

    public void test(String name ){
        System.out.println(name);
    }
}
