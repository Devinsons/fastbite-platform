package com.acme.fastbite.platform.profiles.interfaces.rest.resources;

public record ProfileResource(Long Id,String name, String email, String address, String schedule, String img, Long userId) {
}
