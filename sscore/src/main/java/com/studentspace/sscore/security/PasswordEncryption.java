package com.studentspace.sscore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;

@Configuration
public class PasswordEncryption {

    private final BCryptPasswordEncoder encoder;

    public PasswordEncryption(@Value("${encryption.word}") String encryptionWord) {
        this.encoder = new BCryptPasswordEncoder(12, new SecureRandom(encryptionWord.getBytes()));
    }

    public String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public boolean encryptPasswordMatch(String password, String encryptedPassword) {
        return encoder.matches(password, encryptedPassword);
    }
}
