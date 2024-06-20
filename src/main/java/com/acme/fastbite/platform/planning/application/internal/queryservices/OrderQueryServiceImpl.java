package com.acme.fastbite.platform.planning.application.internal.queryservices;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Order;
import com.acme.fastbite.platform.planning.domain.model.aggregates.OrderItem;
import com.acme.fastbite.platform.planning.domain.services.OrderQueryService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderItemResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderResource> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapOrderToOrderResource)
                .collect(Collectors.toList());
    }

    private OrderResource mapOrderToOrderResource(Order order) {
        List<OrderItemResource> itemResources = order.getItems().stream()
                .map(this::mapOrderItemToOrderItemResource)
                .collect(Collectors.toList());

        return new OrderResource(
                order.getOrderId(),
                order.getCompanyId(),
                order.getRestaurantId(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                itemResources
        );
    }

    private OrderItemResource mapOrderItemToOrderItemResource(OrderItem orderItem) {
        return new OrderItemResource(
                orderItem.getOrderItemId(),
                orderItem.getProductId(),
                orderItem.getQuantity()
        );
    }
}
