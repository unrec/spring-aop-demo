package com.unrec.demo.aop.config;

import com.unrec.demo.aop.aspect.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
public class AspectDependenciesInitializer {

    private static final String INITIALIZING_LOG_MSG = "Initializing '{}' isNull: {}";
    private final LogService logService;

    @EventListener
    public void initAspectDependencies(ApplicationStartedEvent startedEvent) {
        log.info(INITIALIZING_LOG_MSG, "LogService", Objects.isNull(logService));
//        AuditedAspect.setLogService(logService);
    }
}
