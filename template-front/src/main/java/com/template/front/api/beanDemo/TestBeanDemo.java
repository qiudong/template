package com.template.front.api.beanDemo;

import org.springframework.stereotype.Component;

@Component
public class TestBeanDemo implements TestAopInterface {


    String a = "11";

    public TestBeanDemo() {
        System.out.println("初始化component Bean实例 ");
    }

    @Override
    public String test(String a ) {
        return "ss"+a;
    }
}
