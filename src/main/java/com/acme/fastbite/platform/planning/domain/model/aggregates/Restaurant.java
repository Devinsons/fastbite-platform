package com.acme.fastbite.platform.planning.domain.model.aggregates;

import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import com.acme.fastbite.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Restaurant extends AuditableAbstractAggregateRoot<Restaurant> {

    @Getter
    @Embedded
    @Column(name = "restaurant_id")
    private final AcmeRestaurantRecordId acmeRestaurantRecordId;

    @Embedded
    private ProfileId profileId;

    public Restaurant() {
        this.acmeRestaurantRecordId = new AcmeRestaurantRecordId();
    }

    public Restaurant(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public Restaurant(ProfileId profileId) {
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

    public String getRestaurantRecordId() {
        return this.acmeRestaurantRecordId.restaurantRecordId();
    }

}
