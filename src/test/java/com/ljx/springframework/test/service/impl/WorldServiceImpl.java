package com.ljx.springframework.test.service.impl;

import com.ljx.springframework.test.service.WorldService;

/**
 * @Author: ljx
 * @Date: 2023/12/7 14:33
 */
public class WorldServiceImpl implements WorldService {
    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
