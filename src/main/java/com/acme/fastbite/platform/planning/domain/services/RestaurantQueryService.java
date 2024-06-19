package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.queries.GetRestaurantByProfileIdQuery;
import com.acme.fastbite.platform.planning.domain.model.queries.GetRestaurantByAcmeRestaurantRecordIdQuery;

import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> handle(GetRestaurantByProfileIdQuery query);
    Optional<Restaurant> handle(GetRestaurantByAcmeRestaurantRecordIdQuery query);
    List<Restaurant> getAllRestaurants();
}
