package com.ljx.springframework.core.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: ljx
 * @Date: 2023/11/30 18:05
 */
public class DefaultResourceLoader implements ResourceLoader{
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 当成UrlResource处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 当成文件系统下的资源来处理
                String path = location;
                if(location.startsWith("/")) {
                    path = location.substring(1);
                }
                return new FileSystemResource(path);
            }
        }
    }
}
