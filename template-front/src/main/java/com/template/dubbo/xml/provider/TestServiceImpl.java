package com.template.dubbo.xml.provider;

import com.template.dubbo.xml.api.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void get() {
        System.out.println("被调用了。。。。");
    }
}
