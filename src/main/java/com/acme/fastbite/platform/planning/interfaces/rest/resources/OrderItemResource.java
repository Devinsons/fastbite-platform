package com.acme.fastbite.platform.planning.interfaces.rest.resources;

public record OrderItemResource(
        Long orderItemId,
        Long productId,
        int quantity
) {
}