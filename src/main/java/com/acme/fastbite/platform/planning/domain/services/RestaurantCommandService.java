package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateRestaurantCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;

public interface RestaurantCommandService {
    AcmeRestaurantRecordId handle(CreateRestaurantCommand command);
}
