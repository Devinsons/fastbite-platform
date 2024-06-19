package com.acme.fastbite.platform.iam.interfaces.rest.transform;

import com.acme.fastbite.platform.iam.domain.model.aggregates.User;
import com.acme.fastbite.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}