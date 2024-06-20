package com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByRestaurant_AcmeRestaurantRecordId(String restaurantRecordId);
    List<Product> findByRestaurantId(Long restaurantId);
    List<Product> findByRestaurant(Restaurant restaurant);
}
