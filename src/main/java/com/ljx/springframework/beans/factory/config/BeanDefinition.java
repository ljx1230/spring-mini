package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.PropertyValues;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:54
 * BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、是否为单例、bean属性等，此处简化只包含class类型和bean属性
 */
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;
    public BeanDefinition(Class beanClass) {
        this(beanClass,null);
    }
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
    public Class getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
