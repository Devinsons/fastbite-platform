package com.acme.fastbite.platform.planning.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResource(
        Long orderId,
        Long companyId,
        Long restaurantId,
        String orderStatus,
        LocalDateTime createdAt,
        List<OrderItemResource> items
) {
}