package com.acme.fastbite.platform.planning.interfaces.rest.transform;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateRestaurantCommand;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateRestaurantResource;

public class CreateRestaurantCommandFromResourceAssembler {
    public static CreateRestaurantCommand toCommandFromResource(CreateRestaurantResource resource) {
        return new CreateRestaurantCommand(resource.name(), resource.email(), resource.address(), resource.schedule(), resource.image(), resource.userId());
    }
}
