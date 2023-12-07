package com.ljx.springframework.test.aop;

import com.ljx.springframework.aop.*;
import com.ljx.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.ljx.springframework.aop.aspectj.AspectJExpressionPointCutAdvisor;
import com.ljx.springframework.aop.framework.CglibAopProxy;
import com.ljx.springframework.aop.framework.JdkDynamicAopProxy;
import com.ljx.springframework.aop.framework.ProxyFactory;
import com.ljx.springframework.aop.framework.adapter.MethodBeforeInterceptor;
import com.ljx.springframework.test.common.WorldServiceBeforeAdvice;
import com.ljx.springframework.test.common.WorldServiceInterceptor;
import com.ljx.springframework.test.service.WorldService;
import com.ljx.springframework.test.service.impl.WorldServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
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

    @Test
    public void testProxyFactory() throws Exception {
        // 使用jdk动态代理
        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService proxy = (WorldService) proxyFactory.getProxy();
        proxy.hello();

        System.out.println("——————————————————");

        // 使用cglib代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.hello();
    }

    @Test
    public void testBeforeAdvice() throws Exception {
        // 设置BeforeAdvice
        WorldServiceBeforeAdvice beforeAdvice = new WorldServiceBeforeAdvice();
        MethodBeforeInterceptor beforeInterceptor = new MethodBeforeInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(beforeInterceptor);

        WorldService worldService = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        worldService.hello();
    }

    @Test
    public void testAdvisor() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        String expression = "execution(* com.ljx.springframework.test.service.WorldService.hello(..))";
        AspectJExpressionPointCutAdvisor advisor = new AspectJExpressionPointCutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeInterceptor methodBeforeInterceptor = new MethodBeforeInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodBeforeInterceptor);

        ClassFilter classFilter = advisor.getPointCut().getClassFilter();
        if(classFilter.matches(worldService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(worldService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
            proxy.hello();
        }
    }

}
