package com.acme.fastbite.platform.planning.domain.model.commands;

public record CreateOrderItemCommand(Long productId, int quantity) {
}
