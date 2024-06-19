package com.acme.fastbite.platform.planning.application.internal.queryservices;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.queries.GetRestaurantByProfileIdQuery;
import com.acme.fastbite.platform.planning.domain.model.queries.GetRestaurantByAcmeRestaurantRecordIdQuery;
import com.acme.fastbite.platform.planning.domain.services.RestaurantQueryService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantQueryServiceImpl implements RestaurantQueryService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantQueryServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> handle(GetRestaurantByProfileIdQuery query) {
        return restaurantRepository.findByProfileId(query.profileId());
    }

    @Override
    public Optional<Restaurant> handle(GetRestaurantByAcmeRestaurantRecordIdQuery query) {
        return restaurantRepository.findByAcmeRestaurantRecordId(query.acmeRestaurantRecordId());
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
