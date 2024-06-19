package com.acme.fastbite.platform.profiles.interfaces.rest.transform;

import com.acme.fastbite.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.fastbite.platform.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource){
        return new CreateProfileCommand(resource.name(),resource.email(),resource.address(),resource.schedule(),resource.img(),resource.userId());
    }
}
