package com.acme.fastbite.platform.planning.interfaces.rest;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateOrderCommand;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateOrderItemCommand;
import com.acme.fastbite.platform.planning.domain.services.OrderCommandService;
import com.acme.fastbite.platform.planning.domain.services.OrderQueryService;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateOrderResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.OrderResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = "application/json")
@Tag(name = "Orders", description = "Order Management Endpoints")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        CreateOrderCommand command = createOrderCommandFromResource(resource);
        OrderResource orderResource = orderCommandService.createOrder(command);
        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrderDetails(@PathVariable Long orderId) {
        OrderResource orderResource = orderCommandService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderResource);
    }

    // Método para transformar CreateOrderResource a CreateOrderCommand
    private CreateOrderCommand createOrderCommandFromResource(CreateOrderResource resource) {
        List<CreateOrderItemCommand> itemCommands = resource.items().stream()
                .map(item -> new CreateOrderItemCommand(item.productId(), item.quantity()))
                .collect(Collectors.toList());

        return new CreateOrderCommand(resource.companyId(), resource.restaurantId(), itemCommands);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResource> updateOrderStatus(@PathVariable Long orderId) {
        OrderResource updatedOrder = orderCommandService.updateOrderStatus(orderId, "DONE");
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        List<OrderResource> orders = orderQueryService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

}
