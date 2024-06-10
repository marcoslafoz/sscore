package com.studentspace.sscore.auth.login;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService userLoginService;

    @Autowired
    private JwtService jwtService;

    @QueryMapping
    public String login(@Argument String username, @Argument String password) {
        User user = userLoginService.getUserByUsernameAndPassword(username, password);
        return jwtService.generateToken(user);
    }

    @QueryMapping
    public boolean loginFindUsername(@Argument String username){
        User user = userLoginService.getUserByUsername(username);
        return Objects.equals(user.getUsername(), username);
    }

}
