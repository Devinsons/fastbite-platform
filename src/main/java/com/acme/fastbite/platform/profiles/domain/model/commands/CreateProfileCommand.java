package com.acme.fastbite.platform.profiles.domain.model.commands;

public record CreateProfileCommand(String name,String email, String address, String schedule, String img,Long userId) {
}
