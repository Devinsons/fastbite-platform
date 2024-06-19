package com.acme.fastbite.platform.iam.domain.services;

import com.acme.fastbite.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
