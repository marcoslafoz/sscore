package com.studentspace.sscore.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.user.User;
import com.studentspace.sscore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Controller
@RestController
@RequestMapping("/auth")
public class UserLoginQuery {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserService userService;

    private final JwtService jwtService;

    public UserLoginQuery(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/user")
    public List<User> get() {
        return userService.getUserList();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody UserLoginDto loginUserDto) {

        User user = userLoginService.getUserByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());

        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }

    @PostMapping("/context")
    public Integer authContext(@RequestParam("token") String token) {
        if(jwtService.validateToken(token)){
            return Integer.parseInt(jwtService.extractUserId(token));
        }else{
            return null;
        }

    }
}
