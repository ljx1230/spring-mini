package com.ljx.springframework.test.ioc;

import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ljx.springframework.beans.factory.xml.XmlFileBeanDefinitionReader;
import com.ljx.springframework.core.io.DefaultResourceLoader;
import com.ljx.springframework.core.io.Resource;
import com.ljx.springframework.core.io.ResourceLoader;
import com.ljx.springframework.test.ioc.bean.Car;
import com.ljx.springframework.test.ioc.bean.Person;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/11/30 20:04
 */
public class XmlFileDefineBeanTest {
    @Test
    public void testXmlFileParse() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlFileBeanDefinitionReader xmlFileBeanDefinitionReader = new XmlFileBeanDefinitionReader(beanFactory);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:spring.xml");
        xmlFileBeanDefinitionReader.loadBeanDefinitions(resource);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
