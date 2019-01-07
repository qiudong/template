package com.template.front.api.proxyTest;

import java.lang.reflect.Proxy;

public class DyProxy<T> {

    public Object getInstance(Class<T> tClass){

        return Proxy.newProxyInstance(tClass.getClassLoader(),tClass.getInterfaces(),(proxy, method, args) -> {

            Object object = method.invoke(tClass.newInstance(), args);
            return object;
        });
}
}
