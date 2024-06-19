package com.acme.fastbite.platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {

}
