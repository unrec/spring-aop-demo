package com.unrec.demo.aop.aspect.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogService {

    private List<String> logs = new ArrayList<>();

    public void send(String message) {
        log.debug(message);
        logs.add(message);
    }

    public List<String> getLogs() {
        return logs;
    }
}