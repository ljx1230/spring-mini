package com.ljx.springframework.test.aop;

import com.ljx.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.ljx.springframework.test.service.HelloService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: ljx
 * @Date: 2023/12/3 16:27
 */
public class PointcutExpressionTest {
    @Test
    public void testPointcutExpression() throws Exception {
        AspectJExpressionPointCut pointCut
                = new AspectJExpressionPointCut("execution(* com.ljx.springframework.test.service.HelloService.*(..))");
        Class<HelloService> helloServiceClass = HelloService.class;
        Method method = helloServiceClass.getDeclaredMethod("sayHello");
        
        System.out.println(pointCut.matches(helloServiceClass));
        System.out.println(pointCut.matches(method,helloServiceClass));
    }
}
