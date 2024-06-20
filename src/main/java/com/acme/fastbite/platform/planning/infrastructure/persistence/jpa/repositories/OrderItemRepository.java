package com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Order;
import com.acme.fastbite.platform.planning.domain.model.aggregates.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}