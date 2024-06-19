package com.acme.fastbite.platform.iam.interfaces.rest.transform;

import com.acme.fastbite.platform.iam.domain.model.aggregates.User;
import com.acme.fastbite.platform.iam.interfaces.rest.resources.UserResource;
import com.acme.fastbite.platform.iam.domain.model.entities.Role;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
