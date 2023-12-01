package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.ioc.bean.Person;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/1 16:42
 */
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
//        Person person = context.getBean("person", Person.class);
//        System.out.println(person);
        context.close();
    }
}
