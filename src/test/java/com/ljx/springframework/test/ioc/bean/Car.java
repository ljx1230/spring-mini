package com.ljx.springframework.test.ioc.bean;

/**
 * @Author: ljx
 * @Date: 2023/11/30 14:35
 */
public class Car {
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
