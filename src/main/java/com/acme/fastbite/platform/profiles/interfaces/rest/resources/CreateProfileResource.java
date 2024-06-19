package com.acme.fastbite.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(String name,String email, String address, String schedule, String img,Long userId) {
}
