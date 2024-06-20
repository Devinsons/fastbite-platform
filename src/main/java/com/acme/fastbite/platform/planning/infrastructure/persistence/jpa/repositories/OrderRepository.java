package com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(Long orderId);
}
