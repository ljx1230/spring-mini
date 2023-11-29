package com.ljx.springframework.beans;

/**
 * @Author: ljx
 * @Date: 2023/11/29 19:51
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
