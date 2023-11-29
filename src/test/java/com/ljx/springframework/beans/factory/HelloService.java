package com.ljx.springframework.beans.factory;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:37
 */
public class HelloService {


    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    private String foo;

    private String bar;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "HelloService{" +
                "foo='" + foo + '\'' +
                ", bar='" + bar + '\'' +
                '}';
    }
}