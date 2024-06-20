package com.acme.fastbite.platform.planning.domain.model.commands;

import java.util.List;

public record CreateOrderCommand(Long companyId, Long restaurantId, List<CreateOrderItemCommand> items) {
}

