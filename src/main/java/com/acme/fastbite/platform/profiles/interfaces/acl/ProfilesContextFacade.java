package com.acme.fastbite.platform.profiles.interfaces.acl;

import com.acme.fastbite.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.fastbite.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.acme.fastbite.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.fastbite.platform.profiles.domain.services.ProfileCommandService;
import com.acme.fastbite.platform.profiles.domain.services.ProfileQueryService;
import com.acme.fastbite.platform.profiles.interfaces.rest.resources.ProfileResource;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String name, String email, String address, String schedule, String image, Long userId) {
        var createProfileCommand = new CreateProfileCommand(name, email, address, schedule, image, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    public ProfileResource fetchProfileById(Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        return profile.map(p -> new ProfileResource(p.getId(), p.getName(), p.getEmailAddress(), p.getAddress(), p.getSchedule(), p.getImage(), p.getUserId())).orElse(null);
    }

    }
