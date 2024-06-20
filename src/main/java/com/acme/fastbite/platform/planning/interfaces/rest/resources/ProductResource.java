package com.acme.fastbite.platform.planning.interfaces.rest.resources;

public record ProductResource(   Long id,
                                 String name,
                                 String description,
                                 String image,
                                 String type) {
}
