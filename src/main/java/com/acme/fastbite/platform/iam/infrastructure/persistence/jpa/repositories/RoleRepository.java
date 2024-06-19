package com.acme.fastbite.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.iam.domain.model.entities.Role;
import com.acme.fastbite.platform.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}