package com.aws.adventureworks.lt.app.utils;

import com.aws.adventureworks.lt.app.customer.CustomerCreationDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordHandler {
    public static String generatePasswordHash(CustomerCreationDto customerCreationDto){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(customerCreationDto.getPassword());
    }
}
