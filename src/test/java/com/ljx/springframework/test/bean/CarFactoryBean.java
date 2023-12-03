package com.ljx.springframework.test.bean;

import com.ljx.springframework.beans.factory.FactoryBean;

/**
 * @Author: ljx
 * @Date: 2023/12/2 16:05
 */
public class CarFactoryBean implements FactoryBean {
    private String brand;
    @Override
    public Object getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
