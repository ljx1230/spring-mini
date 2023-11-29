package com.ljx.springframework.beans;

/**
 * @Author: ljx
 * @Date: 2023/11/29 19:51
 */
public class PropertyValue {
    private final String name;

    private final String value;

    public PropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
