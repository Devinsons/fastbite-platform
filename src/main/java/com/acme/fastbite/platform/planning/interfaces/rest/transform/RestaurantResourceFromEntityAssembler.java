package com.acme.fastbite.platform.planning.interfaces.rest.transform;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.RestaurantResource;

public class RestaurantResourceFromEntityAssembler {

    public static RestaurantResource toResourceFromEntity(Restaurant restaurant, ExternalProfileService externalProfileService) {
        var profile = externalProfileService.fetchProfileById(restaurant.getProfileId());

        return new RestaurantResource(
                restaurant.getRestaurantRecordId(),
                restaurant.getProfileId(),
                profile.name(),
                profile.email(),
                profile.address(),
                profile.schedule(),
                profile.img()
        );
    }
}
