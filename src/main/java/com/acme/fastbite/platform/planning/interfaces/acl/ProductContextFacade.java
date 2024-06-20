package com.acme.fastbite.platform.planning.interfaces.acl;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;
import com.acme.fastbite.platform.planning.domain.services.ProductCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductContextFacade {
    private final ProductCommandService productCommandService;
    private final ProductRepository productRepository;

    public ProductContextFacade(ProductCommandService productCommandService, ProductRepository productRepository) {
        this.productCommandService = productCommandService;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProductsByRestaurant(String restaurantRecordId) {
        return productRepository.findByRestaurant_AcmeRestaurantRecordId(restaurantRecordId);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}
