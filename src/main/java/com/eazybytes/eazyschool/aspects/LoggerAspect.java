package com.eazybytes.eazyschool.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@Aspect
public class LoggerAspect {
    @Around("execution(* com.eazybytes.eazyschool..*.*(..))")
    public Object log(ProceedingJoinPoint jointPoint) throws Throwable{
        log.info(jointPoint.getSignature().toString(), " method execution start");
        Instant start = Instant.now() ;
        Object returnObject= jointPoint.proceed() ;
        Instant end = Instant.now() ;
        long timeElapsed = Duration.between(start, end).toMillis() ;
        log.info("Time took to execute "+ jointPoint.getSignature().toString() +timeElapsed);
        log.info(jointPoint.getSignature().toString()+"Method execution end");
        return returnObject ;
    }

    @AfterThrowing(value="execution(* com.eazybytes.eazyschool..*.*(..))", throwing = "ex")
    public void logException(JoinPoint jointPoint, Exception ex){
        log.error(jointPoint.getSignature().toString() + "an Exeption happend due to"+ ex.getMessage());
    }
}
