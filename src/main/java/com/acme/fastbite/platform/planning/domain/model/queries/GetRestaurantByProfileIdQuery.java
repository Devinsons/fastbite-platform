package com.acme.fastbite.platform.planning.domain.model.queries;

import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;

public record GetRestaurantByProfileIdQuery(ProfileId profileId) {
}
