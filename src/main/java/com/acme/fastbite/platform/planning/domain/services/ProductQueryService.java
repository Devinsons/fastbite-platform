package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;

import java.util.List;

public interface ProductQueryService {

    List<Product> getAllProducts();
    List<Product> getProductsByRestaurantRecordId(String restaurantRecordId);
}
