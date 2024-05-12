package com.studentspace.sscore.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;

public class PasswordEncryption {

    //TODO: Sacar a application properties
    private static final String ENCRYPTION_WORD = "SECRET-ENCRYPTION_WORD";
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12, new SecureRandom(ENCRYPTION_WORD.getBytes()));

    public static String encryptPassword(String password){
        return ENCODER.encode(password);
    }

    public static boolean encryptPasswordMatch(String password,String encryptedPassword){
        return ENCODER.matches(password, encryptedPassword);
    }


}
