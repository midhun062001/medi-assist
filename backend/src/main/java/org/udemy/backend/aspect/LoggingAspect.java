package org.udemy.backend.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Around("execution(* org.udemy.backend..service..*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        log.info("Entering {}.{} with args: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("Exception in {}.{}: {}", className, methodName, ex.getMessage());
            throw ex;
        }

        long duration = System.currentTimeMillis() - start;
        log.info("Exiting {}.{} returned: {} (Execution time: {} ms)", className, methodName, result, duration);
        log.info("duration: {} ms", duration);
        return result;
    }
}
