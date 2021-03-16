package com.unrec.demo.aop.repository;

import com.unrec.demo.aop.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByFirstName(String name);
}