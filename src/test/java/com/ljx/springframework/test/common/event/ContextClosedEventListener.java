package com.ljx.springframework.test.common.event;

import com.ljx.springframework.context.ApplicationListener;
import com.ljx.springframework.context.event.ContextClosedEvent;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:56
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName() + ">>>关闭事件");
    }
}
