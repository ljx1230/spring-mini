package com.ljx.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import com.ljx.springframework.core.io.DefaultResourceLoader;
import com.ljx.springframework.core.io.Resource;
import com.ljx.springframework.core.io.UrlResource;
import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @Author: ljx
 * @Date: 2023/11/30 18:15
 */
public class ResourceLoaderTest {
    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // 测试类路径下的加载
        Resource resource = defaultResourceLoader.getResource("classpath:ljx.txt");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        // 加载文件系统中的资源
        resource = defaultResourceLoader.getResource("D:\\code\\java-workplace\\spring-mini\\src\\test\\resources\\xxx.txt");
        inputStream = resource.getInputStream();
        s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        // 加载Url资源
        resource = defaultResourceLoader.getResource("https://www.baidu.com");
        if(resource instanceof UrlResource) {
            System.out.println("是网络资源");
            inputStream = resource.getInputStream();
            s = IoUtil.readUtf8(inputStream);
            System.out.println(s);
        } else {
            System.out.println("加载Url资源有问题");
        }

    }
}
