package com.acme.fastbite.platform.profiles.interfaces.rest.transform;

import com.acme.fastbite.platform.profiles.domain.model.aggregates.Profile;
import com.acme.fastbite.platform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(),entity.getName(), entity.getEmailAddress(),entity.getAddress(),entity.getSchedule(),entity.getImage(),entity.getUserId());

    }
}
