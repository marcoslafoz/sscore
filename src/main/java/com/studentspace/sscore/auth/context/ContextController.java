package com.studentspace.sscore.auth.context;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.user.User;
import com.studentspace.sscore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/auth")
public class ContextController {

    @Autowired
    private UserService userService;

    private final JwtService jwtService;

    public ContextController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/context")
    public ContextDto authContext(@RequestParam("token") String token) {
        if(jwtService.validateToken(token)){

            Long userId = Long.parseLong(jwtService.extractUserId(token));
            User user = userService.getUserById(userId);

            ContextDto contextDto = new ContextDto();

            contextDto.setAuthenticated(true);
            contextDto.setUserID(user.getId());

            return contextDto;

        }else{
            return null;
        }

    }
}
