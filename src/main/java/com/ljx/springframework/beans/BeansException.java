package com.ljx.springframework.beans;


/**
 * @Author: ljx
 * @Date: 2023/11/29 18:43
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }
    public BeansException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
