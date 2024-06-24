package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {

    List<Product> getAllProducts();
    List<Product> getProductsByRestaurantRecordId(String restaurantRecordId);
    Optional<Product> getProductById(Long id);
}
