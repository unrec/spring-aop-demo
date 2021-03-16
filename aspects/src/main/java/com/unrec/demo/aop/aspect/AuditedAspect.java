package com.unrec.demo.aop.aspect;

import com.unrec.demo.aop.annotation.Audited;
import com.unrec.demo.aop.aspect.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@RequiredArgsConstructor
@Component
public class AuditedAspect {

    private static final String AUDIT_POINTCUT = "execution(* *(..)) && @annotation(com.unrec.demo.aop.annotation.Audited)";

    private final LogService logService;

    @Pointcut(AUDIT_POINTCUT)
    public void auditCall() {
    }

    @AfterReturning(pointcut = "auditCall()", returning = "returnValue")
    public void afterReturningAudit(JoinPoint joinPoint, Object returnValue) {
        logService.send(String.format("Method '%s' executed at %s, code - %s", getMethodName(joinPoint), LocalDateTime.now(), getAnnotationCode(joinPoint)));
    }

    @AfterThrowing(pointcut = "auditCall()", throwing = "exception")
    public void afterThrowingAudit(JoinPoint joinPoint, Throwable exception) {
        logService.send(String.format("Method '%s' call failed at %s, code - %s", getMethodName(joinPoint), LocalDateTime.now(), getAnnotationCode(joinPoint)));
    }

    private MethodSignature getMethodSignature(JoinPoint joinPoint) {
        return (MethodSignature) joinPoint.getSignature();
    }

    private String getMethodName(JoinPoint joinPoint) {
        return getMethodSignature(joinPoint).getMethod().getName();
    }

    private String getAnnotationCode(JoinPoint joinPoint) {
        Audited annotation = getMethodSignature(joinPoint).getMethod().getAnnotation(Audited.class);
        return annotation.code();
    }
}