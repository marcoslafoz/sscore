package com.studentspace.sscore.login;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordEncryption {

    private static final String ENCRYPTATION_WORD = "marcos";
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12, new SecureRandom(ENCRYPTATION_WORD.getBytes()));

    public static String encryptPassword(String password){
        return ENCODER.encode(password);
    }

    public static boolean encryptPasswordMatch(String password,String encryptedPassword){
        return ENCODER.matches(password, encryptedPassword);
    }


}
