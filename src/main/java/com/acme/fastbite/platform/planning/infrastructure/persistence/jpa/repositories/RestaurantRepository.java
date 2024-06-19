package com.acme.fastbite.platform.planning.infrastructure.persistence.jpa.repositories;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Restaurant;;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.ProfileId;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByAcmeRestaurantRecordId(AcmeRestaurantRecordId restaurantRecordId);
    Optional<Restaurant> findByProfileId(ProfileId profileId);
}
