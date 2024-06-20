package com.acme.fastbite.platform.planning.interfaces.rest.transform;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource, String restaurantRecordId) {
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.image(),
                resource.type(),
                new AcmeRestaurantRecordId(restaurantRecordId)
        );
    }
}