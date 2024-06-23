package com.acme.fastbite.platform.planning.application.internal.commandservices;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Company;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateCompanyCommand;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;
import com.acme.fastbite.platform.planning.domain.services.CompanyCommandService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;
    private final ExternalProfileService externalProfileService;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository, ExternalProfileService externalProfileService) {
        this.companyRepository = companyRepository;
        this.externalProfileService = externalProfileService;
    }
    
    @Override
    public AcmeCompanyRecordId handle(CreateCompanyCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.name(), command.email() , command.address(), command.schedule(), command.image(), command.userId());
        }
        else {
            companyRepository.findByProfileId(profileId.get()).ifPresent(company -> {
                throw new IllegalArgumentException("Company already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var company = new Company(profileId.get());
        companyRepository.save(company);
        return company.getAcmeCompanyRecordId();
    }
}
