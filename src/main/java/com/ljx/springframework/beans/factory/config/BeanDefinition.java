package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.PropertyValues;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:54
 * BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、bean属性等，
 * 此处简化只包含class类型和bean属性以及是否为单例
 */
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private static final String SCOPE_SINGLETON = "singleton";

    private static final String SCOPE_PROTOTYPE = "prototype";

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = this.scope.equals(SCOPE_SINGLETON);
        this.prototype = this.scope.equals(SCOPE_PROTOTYPE);
    }

    public boolean isPrototype() {
        return prototype;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

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
