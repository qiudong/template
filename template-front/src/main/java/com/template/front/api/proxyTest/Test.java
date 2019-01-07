package com.template.front.api.proxyTest;

import org.hibernate.validator.internal.util.privilegedactions.NewProxyInstance;

public class Test {

    public static void main(String[] args){
//        静态代理
//        StaticProxy proxy = new StaticProxy(new Persion());
//        proxy.invoke();

        // 动态代理

        Person person = (Person) new DyProxy().getInstance(Persion.class);
        person.dosomtion();
    }
}
