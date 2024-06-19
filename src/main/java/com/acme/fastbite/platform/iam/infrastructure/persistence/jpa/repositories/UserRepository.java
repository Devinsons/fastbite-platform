package com.acme.fastbite.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.iam.domain.model.aggregates.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
