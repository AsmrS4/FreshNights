package com.project.pet.helpers.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderManager {
    @Autowired
    private static PasswordEncoder passwordEncoder;

    public PasswordEncoderManager(@Autowired PasswordEncoder passwordEncoder) {
        PasswordEncoderManager.passwordEncoder = passwordEncoder;
    }

    public static boolean isPasswordCorrect(String password, String hashPassword) {
        return passwordEncoder.matches(password, hashPassword);
    }

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
