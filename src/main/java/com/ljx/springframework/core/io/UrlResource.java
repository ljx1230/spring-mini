package com.ljx.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: ljx
 * @Date: 2023/11/30 17:59
 * 对java.net.URL进行资源定位的实现类
 */
public class UrlResource implements Resource{
    private final URL url;
    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException e) {
            throw e;
        }
    }
}
