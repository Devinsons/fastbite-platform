package com.acme.fastbite.platform.planning.interfaces.rest.resources;

public record CreateRestaurantResource(String name,String email,String address,String schedule,String image,Long userId) {
}
