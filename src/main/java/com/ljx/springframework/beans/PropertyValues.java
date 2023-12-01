package com.ljx.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljx
 * @Date: 2023/11/29 19:51
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();
    public void addPropertyValue(PropertyValue propertyValue) {
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(propertyValue.getName())) {
                //覆盖原有的属性值
                this.propertyValueList.set(i, propertyValue);
                return;
            }
        }
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName) {
        for(var propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
