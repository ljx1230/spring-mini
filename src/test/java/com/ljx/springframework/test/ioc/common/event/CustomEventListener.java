package com.ljx.springframework.test.ioc.common.event;

import com.ljx.springframework.context.ApplicationListener;

/**
 * @Author: ljx
 * @Date: 2023/12/3 15:00
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName() + ">>>自定义事件");
    }
}
