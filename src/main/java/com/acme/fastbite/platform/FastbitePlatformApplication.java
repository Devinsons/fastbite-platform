package com.acme.fastbite.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable JPA Auditing
public class FastbitePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastbitePlatformApplication.class, args);
    }

}
