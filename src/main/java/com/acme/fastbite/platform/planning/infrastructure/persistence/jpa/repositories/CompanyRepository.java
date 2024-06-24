package com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Company;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByAcmeCompanyRecordId(AcmeCompanyRecordId companyRecordId);
    Optional<Company> findByProfileId(ProfileId profileId);
    Optional<Company> findById(Long id);
}
