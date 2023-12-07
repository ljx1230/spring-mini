package com.ljx.springframework.test.aop;

import com.ljx.springframework.aop.AdvisedSupport;
import com.ljx.springframework.aop.MethodMatcher;
import com.ljx.springframework.aop.TargetSource;
import com.ljx.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.ljx.springframework.aop.framework.CglibAopProxy;
import com.ljx.springframework.aop.framework.JdkDynamicAopProxy;
import com.ljx.springframework.test.common.WorldServiceInterceptor;
import com.ljx.springframework.test.service.WorldService;
import com.ljx.springframework.test.service.impl.WorldServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: ljx
 * @Date: 2023/12/7 14:34
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);

        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();

        MethodMatcher methodMatcher =
                new AspectJExpressionPointCut("execution(* com.ljx.springframework.test.service.WorldService.hello(..))")
                        .getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);

    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.hello();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.hello();
    }

}
