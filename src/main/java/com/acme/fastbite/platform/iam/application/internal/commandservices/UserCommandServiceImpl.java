package com.acme.fastbite.platform.iam.application.internal.commandservices;

import com.acme.fastbite.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.fastbite.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.fastbite.platform.iam.domain.model.aggregates.User;
import com.acme.fastbite.platform.iam.domain.model.commands.SignInCommand;
import com.acme.fastbite.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.fastbite.platform.iam.domain.model.entities.Role;
import com.acme.fastbite.platform.iam.domain.model.valueobjects.Roles;
import com.acme.fastbite.platform.iam.domain.services.UserCommandService;

import com.acme.fastbite.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.fastbite.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        /**
         *  var roles = command.roles();
         *         if (roles.isEmpty()) {
         *             var role = roleRepository.findByName(Roles.ROLE_USER);
         *             if (role.isPresent()) roles.add(role.get());
         *         } else roles = roles.stream().filter(role -> roleRepository.findByName(role.getName()).isPresent()).toList();
         */
        var roles = new ArrayList<Role>();
        if(command.roles().isEmpty()){
            var role =roleRepository.findByName(Roles.ROLE_USER);
            if(role.isPresent()) roles.add(role.get());
        }else {
            for(var role:command.roles()){
                var existingRole = roleRepository.findByName(role.getName());
                existingRole.ifPresent(roles::add);
            }
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
