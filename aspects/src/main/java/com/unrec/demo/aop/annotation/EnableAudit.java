package com.unrec.demo.aop.annotation;

import com.unrec.demo.aop.config.AuditConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({AuditConfiguration.class})
public @interface EnableAudit {

}