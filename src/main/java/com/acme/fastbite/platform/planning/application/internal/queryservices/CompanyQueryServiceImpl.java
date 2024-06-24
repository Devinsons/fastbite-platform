package com.acme.fastbite.platform.planning.application.internal.queryservices;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Company;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByAcmeCompanyRecordIdQuery;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByIdQuery;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByProfileIdQuery;
import com.acme.fastbite.platform.planning.domain.services.CompanyQueryService;
import com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    private final CompanyRepository companyRepository;

    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
    @Override
    public Optional<Company> handle(GetCompanyByProfileIdQuery query) {
        return companyRepository.findByProfileId(query.profileId());
    }
    @Override
    public Optional<Company> handle(GetCompanyByAcmeCompanyRecordIdQuery query) {
        return companyRepository.findByAcmeCompanyRecordId(query.acmeCompanyRecordId());
    }

    @Override
    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query) {
        return companyRepository.findById(query.id());
    }
}
