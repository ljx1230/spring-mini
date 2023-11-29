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
}