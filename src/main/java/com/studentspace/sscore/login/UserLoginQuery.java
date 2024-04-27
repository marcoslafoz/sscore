package com.studentspace.sscore.login;

import com.studentspace.sscore.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api")
public class UserLoginQuery {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserLogin loginParams) {

        User user = userLoginService.getUserByUsernameAndPassword(loginParams.getUsername(), loginParams.getPassword());

        return user != null;

    }
}
