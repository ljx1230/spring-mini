package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.ioc.bean.Car;
import com.ljx.springframework.test.ioc.bean.Person;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/2 15:27
 */
public class ScopeTest {
    @Test
    public void testScope() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:prototype.xml");
        Car car = context.getBean("car", Car.class);
        Car car1 = context.getBean("car", Car.class);
        System.out.println(car1 == car);
        System.out.println(car.hashCode());
        System.out.println(car1.hashCode());
        System.out.println("---------------------");
        Person person = context.getBean("person", Person.class);
        Person person1 = context.getBean("person", Person.class);
        System.out.println(person1 == person);
        System.out.println(person1.hashCode());
        System.out.println(person.hashCode());
        context.registerShutdownHook();
    }
}
