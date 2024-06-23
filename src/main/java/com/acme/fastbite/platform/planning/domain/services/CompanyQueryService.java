package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Company;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByAcmeCompanyRecordIdQuery;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryService {
    Optional<Company> handle(GetCompanyByProfileIdQuery query);
    Optional<Company> handle(GetCompanyByAcmeCompanyRecordIdQuery query);
    List<Company> getAllCompanys();
}
