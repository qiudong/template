package com.template.spring.beanfactoryProcessor;

import com.template.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name :
                beanDefinitionNames) {
            System.out.println(name);
        }


    }
}
