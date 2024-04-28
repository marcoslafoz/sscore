package com.studentspace.sscore.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginDto loginUserDto) {

        User user = userLoginService.getUserByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());

        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        //return loginResponse;
        return ResponseEntity.ok(loginResponse);
    }
}
