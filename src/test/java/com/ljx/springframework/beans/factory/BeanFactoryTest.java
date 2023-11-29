package com.ljx.springframework.beans.factory;

import com.ljx.springframework.beans.PropertyValue;
import com.ljx.springframework.beans.PropertyValues;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {
    @Test
    public void testBeanFactory() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("foo","hello"));
        propertyValues.addPropertyValue(new PropertyValue("bar","world"));

        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("helloService",beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
        System.out.println(helloService);
    }

}
