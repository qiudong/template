package com.template.spring.beanfactoryProcessor;

import com.template.spring.bean.Blue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Red {

    @Autowired
    private Yellow yellow;

    public Yellow getYellow() {
        return yellow;
    }

    public void setYellow(Yellow yellow) {
        this.yellow = yellow;
    }
}
