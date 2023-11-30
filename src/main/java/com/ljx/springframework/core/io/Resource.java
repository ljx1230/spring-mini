package com.ljx.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Author: ljx
 * @Date: 2023/11/30 17:39
 * 资源的抽象和访问的接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

}
