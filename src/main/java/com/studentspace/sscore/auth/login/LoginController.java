package com.studentspace.sscore.auth.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService userLoginService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginUserDto) {

        User user = userLoginService.getUserByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());
        return jwtService.generateToken(user);
    }

}
