package com.acme.fastbite.platform.iam.interfaces.rest.transform;

import com.acme.fastbite.platform.iam.domain.model.commands.SignInCommand;
import com.acme.fastbite.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}