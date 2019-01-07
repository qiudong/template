package com.template.front.api.proxyTest;

public class StaticProxy {

    Person person;

    public StaticProxy(Person person) {
        this.person = person;
    }

    public void invoke(){
        person.dosomtion();
    }
}
