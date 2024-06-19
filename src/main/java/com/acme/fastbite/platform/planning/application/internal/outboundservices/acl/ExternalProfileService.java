package com.acme.fastbite.platform.planning.application.internal.outboundservices.acl;

import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;
import com.acme.fastbite.platform.profiles.interfaces.acl.ProfilesContextFacade;
import com.acme.fastbite.platform.profiles.interfaces.rest.resources.ProfileResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<ProfileId> fetchProfileIdByEmail(String email){
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    public Optional<ProfileId> createProfile(String name, String email, String address, String schedule, String image, Long userId){
        var profileId = profilesContextFacade.createProfile(name, email, address, schedule, image, userId);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    public ProfileResource fetchProfileById(Long profileId) {
        return profilesContextFacade.fetchProfileById(profileId);
    }

}
