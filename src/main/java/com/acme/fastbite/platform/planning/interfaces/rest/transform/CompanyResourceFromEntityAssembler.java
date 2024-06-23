package com.acme.fastbite.platform.planning.interfaces.rest.transform;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.aggregates.Company;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CompanyResource;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company company, ExternalProfileService externalProfileService) {
        var profile = externalProfileService.fetchProfileById(company.getProfileId());

        return new CompanyResource(
                company.getCompanyRecordId(),
                company.getProfileId(),
                profile.name(),
                profile.email(),
                profile.address(),
                profile.schedule(),
                profile.img()
        );
    }
}
