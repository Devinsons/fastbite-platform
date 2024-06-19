package com.acme.fastbite.platform.planning.domain.model.commands;

public record CreateRestaurantCommand(String name,String email,String address,String schedule,String image,Long userId) {
}
