package com.unrec.demo.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.unrec.demo.aop.aspect"})
public class AuditConfiguration {

  public static final String AUDIT_PROPERTIES_PREFIX = "app.audit";
}