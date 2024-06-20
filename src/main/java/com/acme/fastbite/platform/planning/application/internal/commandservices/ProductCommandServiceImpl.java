package com.acme.fastbite.platform.planning.application.internal.commandservices;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;
import com.acme.fastbite.platform.planning.domain.services.ProductCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository, RestaurantRepository restaurantRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Product handle(CreateProductCommand command) {
        Restaurant restaurant = restaurantRepository.findByAcmeRestaurantRecordId(command.restaurantRecordId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Product product = new Product(command.name(), command.description(), command.image(), command.type(), restaurant);
        return productRepository.save(product);
    }
}


