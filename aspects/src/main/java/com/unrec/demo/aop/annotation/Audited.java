package com.unrec.demo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audited {

  String code();

  boolean excludeArgs() default false;

  boolean excludeReturnValue() default false;

  String[] toExcludeFromReturn() default {};

}