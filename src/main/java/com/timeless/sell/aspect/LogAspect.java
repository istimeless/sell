package com.timeless.sell.aspect;

import com.timeless.sell.annotation.AopLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author lijiayin
 */
@Aspect
public class LogAspect {
    
    @Around(value = "@annotation(aopLog)")
    public Object log(ProceedingJoinPoint pjp, AopLog aopLog) throws Throwable {
        Object[] args = pjp.getArgs();
        return pjp.proceed(args);
    }
}
