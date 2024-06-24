package com.acme.fastbite.platform.planning.interfaces.rest.resources;

public record RestaurantResource(Long id,String acmeRestaurantRecordId ,Long profileId,String name,String email,String address,String schedule,String image) {
}
