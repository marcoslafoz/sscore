package com.studentspace.sscore.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.user.User;
import com.studentspace.sscore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody UserLoginDto loginUserDto) {

        User user = userLoginService.getUserByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());
        return jwtService.generateToken(user);
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
