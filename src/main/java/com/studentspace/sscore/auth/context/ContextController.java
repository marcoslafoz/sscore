package com.studentspace.sscore.auth.context;

import com.studentspace.sscore.user.User;
import com.studentspace.sscore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RestController
@RequestMapping("/auth")
public class ContextController {

    @Autowired
    private UserService userService;

    @PostMapping("/context")
    public ContextDto authContext() {

        ContextDto contextDto = new ContextDto();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();

            if (user != null) {
                contextDto.setAuthenticated(true);
                contextDto.setUserID(user.getId());
            }

        } catch (Exception ignored) {
            System.out.println("--> Inicio de sesion fallido");
        }

        return contextDto;


    }

}
