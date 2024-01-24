package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.bean.Car;
import com.ljx.springframework.test.bean.Person;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/1 14:46
 */
public class ApplicationContextTest {
    @Test
    public void testApplication() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);

        Car car = context.getBean("car",Car.class);
        System.out.println(car);

    }
}
