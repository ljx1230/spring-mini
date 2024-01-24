package com.ljx.springframework.aop.aspectj;

import com.ljx.springframework.aop.PointCut;
import org.aopalliance.aop.Advice;

/**
 * @Author: ljx
 * @Date: 2023/12/7 16:02
 * aspectJ表达式的advisor
 */
public class AspectJExpressionPointCutAdvisor {
    private AspectJExpressionPointCut pointCut;
    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public PointCut getPointCut() {
        if(pointCut == null) {
            pointCut = new AspectJExpressionPointCut(expression);
        }
        return pointCut;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
