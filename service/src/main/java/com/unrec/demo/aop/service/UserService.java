package com.unrec.demo.aop.service;

import com.unrec.demo.aop.annotation.Audited;
import com.unrec.demo.aop.domain.UserEntity;
import com.unrec.demo.aop.exception.NotFoundException;
import com.unrec.demo.aop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Audited(code = "42")
    public UserEntity getUserByName(String name) {
        return userRepository.findByFirstName(name).orElseThrow(() -> new NotFoundException("User not found"));
    }
}