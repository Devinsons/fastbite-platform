package com.acme.fastbite.platform.planning.interfaces.rest.resources;

import java.util.List;

public record CreateOrderResource(Long companyId, Long restaurantId, List<CreateOrderItemResource> items) {
}

