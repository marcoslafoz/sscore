package com.studentspace.sscore.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Controller
@RestController
@RequestMapping("/api")
public class UserLoginQuery {

    private final JwtService jwtService;

    public UserLoginQuery(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody UserLoginDto loginUserDto) {

        User user = userLoginService.getUserByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());

        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
        //return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/test")
    public boolean test(@RequestParam("token") String token) {

       return jwtService.validateToken(token);
    }
}
