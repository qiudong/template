package com.template.front.api.beanDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class Application {

    public static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfigurationBeanDemo.class);


        TestAopInterface baseBean =  (TestAopInterface)annotationConfigApplicationContext.getBean("testBeanDemo");
        baseBean.test("11111111111111111111111");


    }
}
