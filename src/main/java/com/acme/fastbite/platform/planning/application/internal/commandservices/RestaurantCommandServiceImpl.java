package com.acme.fastbite.platform.planning.application.internal.commandservices;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateRestaurantCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import com.acme.fastbite.platform.planning.domain.services.RestaurantCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final ExternalProfileService externalProfileService;

    public RestaurantCommandServiceImpl(RestaurantRepository restaurantRepository, ExternalProfileService externalProfileService) {
        this.restaurantRepository = restaurantRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public AcmeRestaurantRecordId handle(CreateRestaurantCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.name(), command.email() , command.address(), command.schedule(), command.image(), command.userId());
        }
        else {
            restaurantRepository.findByProfileId(profileId.get()).ifPresent(restaurant -> {
                throw new IllegalArgumentException("Restaurant already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var restaurant = new Restaurant(profileId.get());
        restaurantRepository.save(restaurant);
        return restaurant.getAcmeRestaurantRecordId();
    }
}
