package com.acme.fastbite.platform.iam.infrastructure.hashing.bcrypt;

import com.acme.fastbite.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
