package com.acme.fastbite.platform.planning.interfaces.rest;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import com.acme.fastbite.platform.planning.domain.services.ProductCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateProductResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.ProductResource;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/restaurants/{restaurantRecordId}/products", produces = "application/json")
@Tag(name = "ProductsByProducts", description = "Product X Restaurant Management Endpoints")
public class ProductByRestaurantController {

    private final ProductCommandService productCommandService;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    public ProductByRestaurantController(ProductCommandService productCommandService, RestaurantRepository restaurantRepository, ProductRepository productRepository) {
        this.productCommandService = productCommandService;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@PathVariable String restaurantRecordId,
                                                         @RequestBody CreateProductResource resource) {
        CreateProductCommand command = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource, restaurantRecordId);
        Product product = productCommandService.handle(command);
        ProductResource productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product);
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> listProducts(@PathVariable String restaurantRecordId) {
        AcmeRestaurantRecordId acmeRestaurantRecordId = new AcmeRestaurantRecordId(restaurantRecordId);
        Restaurant restaurant = restaurantRepository.findByAcmeRestaurantRecordId(acmeRestaurantRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        List<Product> products = productRepository.findByRestaurant(restaurant);
        List<ProductResource> productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResources);
    }
}
