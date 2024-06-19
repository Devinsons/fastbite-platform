package com.acme.fastbite.platform.profiles.domain.model.queries;

import com.acme.fastbite.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
