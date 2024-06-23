package com.acme.fastbite.platform.profiles.domain.model.commands;

public record UpdateProfileCommand(String name, String address, String schedule, String img) {
}
