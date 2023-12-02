package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.ioc.bean.Car;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/2 16:03
 */
public class FactoryBeanTest {
    @Test
    public void testFactoryBeanTest() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:factoryBean.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
    }
}
