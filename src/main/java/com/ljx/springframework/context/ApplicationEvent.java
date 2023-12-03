package com.ljx.springframework.context;

import java.util.EventObject;

/**
 * @Author: ljx
 * @Date: 2023/12/3 13:52
 */
public abstract class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
