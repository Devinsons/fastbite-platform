package com.acme.fastbite.platform.iam.domain.model.queries;

import com.acme.fastbite.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
