package com.ljx.springframework.test.common.event;

import com.ljx.springframework.context.ApplicationListener;
import com.ljx.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:58
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName() + ">>>刷新事件");
    }
}
