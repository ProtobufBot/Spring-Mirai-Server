package net.lz1998.pbbot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class MyAspect {


    @Pointcut("execution(public * net.lz1998.pbbot.bot.ApiSender.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    private Object logHandler(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info(pjp.getSignature() + " 被调用");
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("Handling error");
            throw e;
        }
    }
}
