package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.ioc.common.event.CustomEvent;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:54
 */
public class EventTest {
    @Test
    public void testEvent() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:event.xml");
        context.publishEvent(new CustomEvent(context));
        context.registerShutdownHook();
    }
}
