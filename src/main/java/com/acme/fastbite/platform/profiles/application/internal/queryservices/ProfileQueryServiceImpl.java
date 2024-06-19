package com.acme.fastbite.platform.profiles.application.internal.queryservices;

import com.acme.fastbite.platform.profiles.domain.model.aggregates.Profile;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.acme.fastbite.platform.profiles.domain.services.ProfileQueryService;
import com.acme.fastbite.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
