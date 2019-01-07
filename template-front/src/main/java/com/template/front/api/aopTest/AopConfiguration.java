package com.template.front.api.aopTest;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy
@Configuration
public class AopConfiguration {
//
    @Pointcut("execution(* com.template.front.api.beanDemo.*.*(..))")
    public void executeService()
    {

    }


    @Before("executeService() &&"+"args(name)")
    public void before(String name)
    {
        System.out.println("before say hello");
        System.out.println("name = "+name);
    }

    @After("executeService()")
    public void after()
    {
        System.out.println("after return");
    }
}
