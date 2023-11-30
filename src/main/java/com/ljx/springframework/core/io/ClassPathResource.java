package com.ljx.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Author: ljx
 * @Date: 2023/11/30 17:46
 * 类路径下资源实现类
 */
public class ClassPathResource implements Resource{
    private final String path;
    public ClassPathResource(String path) {
        this.path = path;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if(is == null) {
            throw new FileNotFoundException(this.path + " 不存在,打开失败");
        }
        return is;
    }

    public String getPath() {
        return path;
    }
}
