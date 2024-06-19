package com.acme.fastbite.platform.profiles.domain.services;


import com.acme.fastbite.platform.profiles.domain.model.aggregates.Profile;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
