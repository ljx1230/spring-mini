package com.ljx.springframework.test.ioc;

import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ljx.springframework.beans.factory.xml.XmlFileBeanDefinitionReader;
import com.ljx.springframework.test.bean.Car;
import com.ljx.springframework.test.bean.Person;
import com.ljx.springframework.test.common.CustomBeanFactoryPostProcessor;
import com.ljx.springframework.test.common.CustomerBeanPostProcessor;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:32
 */
public class BeanFactoryPostProcessAndBeanPostProcessorTest {
    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlFileBeanDefinitionReader beanDefinitionReader = new XmlFileBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //在所有BeanDefinition加载完成后，但在bean实例化之前，修改BeanDefinition的属性值
        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    @Test
    public void testBeanPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlFileBeanDefinitionReader beanDefinitionReader = new XmlFileBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 添加bean实例化后的处理器
        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
