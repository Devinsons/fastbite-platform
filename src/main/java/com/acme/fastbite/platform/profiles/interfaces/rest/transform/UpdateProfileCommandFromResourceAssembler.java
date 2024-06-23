package com.acme.fastbite.platform.profiles.interfaces.rest.transform;

import com.acme.fastbite.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.acme.fastbite.platform.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource){
        return new UpdateProfileCommand(resource.name(), resource.address(), resource.schedule(), resource.img());
    }
}