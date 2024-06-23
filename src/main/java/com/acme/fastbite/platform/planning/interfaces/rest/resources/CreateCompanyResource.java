package com.acme.fastbite.platform.planning.interfaces.rest.resources;

public record CreateCompanyResource(String name,String email,String address,String schedule,String image,Long userId) {
}
