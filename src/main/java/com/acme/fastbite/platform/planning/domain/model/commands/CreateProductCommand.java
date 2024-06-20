package com.acme.fastbite.platform.planning.domain.model.commands;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;

public record CreateProductCommand( String name,
                                    String description,
                                    String image,
                                    String type,
                                    AcmeRestaurantRecordId restaurantRecordId) {
}
