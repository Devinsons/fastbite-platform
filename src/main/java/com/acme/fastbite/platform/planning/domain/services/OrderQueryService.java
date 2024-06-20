package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderResource;

import java.util.List;

public interface OrderQueryService {
    List<OrderResource> getAllOrders();
}
