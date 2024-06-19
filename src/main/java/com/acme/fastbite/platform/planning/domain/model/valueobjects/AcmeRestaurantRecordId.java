package com.acme.fastbite.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeRestaurantRecordId(String restaurantRecordId) {
    public AcmeRestaurantRecordId() {
        this(UUID.randomUUID().toString());
    }

    public AcmeRestaurantRecordId {
        if (restaurantRecordId == null || restaurantRecordId.isBlank()) {
            throw new IllegalArgumentException("Restaurant restaurantRecordId cannot be null or blank");
        }
    }
}
