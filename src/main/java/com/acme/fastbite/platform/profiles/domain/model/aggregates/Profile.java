package com.acme.fastbite.platform.profiles.domain.model.aggregates;

import com.acme.fastbite.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.fastbite.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.acme.fastbite.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.fastbite.platform.profiles.domain.model.valueobjects.UserId;
import com.acme.fastbite.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank
    @Getter
    @Setter
    @Size(max = 100)
    private String name;

    @Embedded
    private EmailAddress email;


    @Getter
    @Setter
    @Size(max = 255)
    private String address;

    @Getter
    @Setter
    @Size(max = 100)
    private String schedule;

    @Getter
    @Setter
    @Size(max = 255)
    private String image;

    @Embedded
    private UserId userId;

    public Profile(String name,String email, String address, String schedule, String image, Long userId) {
        this.name = name;
        this.email = new EmailAddress(email);
        this.address = address;
        this.schedule = schedule;
        this.image = image;
        this.userId = new UserId(userId);
    }

    public Profile(CreateProfileCommand command){
        this.name = command.name();
        this.email = new EmailAddress(command.email());
        this.address = command.address();
        this.schedule = command.schedule();
        this.image = command.img();
        this.userId = new UserId(command.userId());
    }
    public void updateProfile(UpdateProfileCommand command) {
        this.name = command.name();
        this.address = command.address();
        this.schedule = command.schedule();
        this.image = command.img();
    }

    public Profile(Long userId) {
        this();
        this.userId = new UserId(userId);
    }

    public Profile(UserId userId) {
        this();
        this.userId = userId;
    }

    public Profile() {
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public Long profileId() {
        return this.id;
    }



    public String getEmailAddress() { return email.email(); }

    public Long getUserId() {  return userId != null ? userId.userId() : null; }


}
