package com.ljx.springframework.test.ioc;

import com.ljx.springframework.beans.PropertyValue;
import com.ljx.springframework.beans.PropertyValues;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanReference;
import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ljx.springframework.test.bean.Car;
import com.ljx.springframework.test.bean.Person;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/11/30 14:37
 */
public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","ljx"));
        propertyValues.addPropertyValue(new PropertyValue("age",21));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("person",beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册Car示例
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("brand","byd"));
        BeanDefinition beanDefinition = new BeanDefinition(Car.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("car",beanDefinition);

        // 注册Person对象，Person对象中组合了Car对象
        propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","ljx"));
        propertyValues.addPropertyValue(new PropertyValue("age",21));
        propertyValues.addPropertyValue(new PropertyValue("car",new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("person",personBeanDefinition);

        System.out.println(beanFactory.getBean("car"));
        System.out.println(beanFactory.getBean("person"));

    }
}
