package com.acme.fastbite.platform.planning.interfaces.rest;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.services.ProductCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.ProductResource;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/products", produces = "application/json")
@Tag(name = "Products", description = "Product Management Endpoints")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    public ProductController(ProductCommandService productCommandService, RestaurantRepository restaurantRepository, ProductRepository productRepository) {
        this.productCommandService = productCommandService;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> listAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductResource> productResources = allProducts.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResources);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }
}
