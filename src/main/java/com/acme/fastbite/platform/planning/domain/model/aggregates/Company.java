package com.acme.fastbite.platform.planning.domain.model.aggregates;

import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;
import com.acme.fastbite.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Company extends AuditableAbstractAggregateRoot<Company> {
    @Getter
    @Embedded
    @Column(name = "company_id")
    private final AcmeCompanyRecordId acmeCompanyRecordId;

    @Embedded
    private ProfileId profileId;

    public Company() {
        this.acmeCompanyRecordId = new AcmeCompanyRecordId();
    }

    public Company(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public Company(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    public void updateProfileId(ProfileId profileId) {
        this.profileId = profileId;
    }

    public void updateProfileId(Long profileId) {
        this.profileId = new ProfileId(profileId);
    }

    public Long getProfileId() {
        return profileId.profileId();
    }

    public String getCompanyRecordId() {
        return this.acmeCompanyRecordId.companyRecordId();
    }

}
