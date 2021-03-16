package com.unrec.demo.aop;

import com.unrec.demo.aop.annotation.EnableAudit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAudit
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}