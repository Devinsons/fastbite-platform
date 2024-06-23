package com.acme.fastbite.platform.planning.interfaces.rest.transform;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateCompanyCommand;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateCompanyResource;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(CreateCompanyResource resource) {
        return new CreateCompanyCommand(resource.name(), resource.email(), resource.address(), resource.schedule(), resource.image(), resource.userId());
    }
}
