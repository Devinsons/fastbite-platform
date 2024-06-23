package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateCompanyCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;

public interface CompanyCommandService {
    AcmeCompanyRecordId handle(CreateCompanyCommand command);
}
