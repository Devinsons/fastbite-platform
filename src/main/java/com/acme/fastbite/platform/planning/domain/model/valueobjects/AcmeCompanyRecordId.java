package com.acme.fastbite.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeCompanyRecordId(String companyRecordId) {

    public AcmeCompanyRecordId() {
        this("company-" + UUID.randomUUID().toString());
    }

    public AcmeCompanyRecordId {
        if (companyRecordId == null || companyRecordId.isBlank()) {
            throw new IllegalArgumentException("Acme company record profileId cannot be null or blank");
        }
    }

}
