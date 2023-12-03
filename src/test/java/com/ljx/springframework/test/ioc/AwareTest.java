package com.ljx.springframework.test.ioc;

import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljx.springframework.test.bean.Person;
import com.ljx.springframework.test.service.HelloService;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: ljx
 * @Date: 2023/12/2 13:47
 */
public class AwareTest {
    @Test
    public void testAware() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        Arrays.stream(helloService.getApplicationContext().getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(helloService.getBeanFactory().getBean("person", Person.class));
    }
}
