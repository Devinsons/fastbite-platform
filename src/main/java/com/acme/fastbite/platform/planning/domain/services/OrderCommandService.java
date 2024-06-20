package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Order;
import com.acme.fastbite.platform.planning.domain.model.aggregates.OrderItem;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateOrderCommand;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateOrderItemCommand;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.OrderItemRepository;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderItemResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderResource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderCommandService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResource createOrder(CreateOrderCommand command) {
        Order order = new Order();
        order.setCompanyId(command.companyId());
        order.setRestaurantId(command.restaurantId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CreateOrderItemCommand itemCommand : command.items()) {
            Product product = productRepository.findById(itemCommand.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + itemCommand.productId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemCommand.productId());
            orderItem.setQuantity(itemCommand.quantity());
            orderItem.setOrder(order); // Set the order for the order item
            orderItems.add(orderItem);
        }

        order.setItems(orderItems); // Set the list of order items for the order
        orderRepository.save(order);

        return mapOrderToOrderResource(order);
    }

    public OrderResource getOrderDetails(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));
        return mapOrderToOrderResource(order);
    }

    private OrderResource mapOrderToOrderResource(Order order) {
        List<OrderItemResource> itemResources = order.getItems().stream()
                .map(item -> new OrderItemResource(item.getOrderItemId(), item.getProductId(), item.getQuantity()))
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

    @Transactional
    public OrderResource updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        order.setOrderStatus(newStatus);
        orderRepository.save(order);

        return mapOrderToOrderResource(order);
    }
}