package com.studentspace.sscore.auth.context;

import com.studentspace.sscore.security.JwtService;
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

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();

            ContextDto contextDto = new ContextDto();

            contextDto.setAuthenticated(true);
            contextDto.setUserID(user.getId());

            return contextDto;

    }

    @GetMapping("/me")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
        //return ResponseEntity.ok(currentUser);
    }
}
