package com.studentspace.sscore.domain.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @MutationMapping
    public User userCreate(@Argument User user) {
        boolean isCreated = userService.save(user);
        if (isCreated) return user;
        
        return null;
    }

    @QueryMapping
    public User userRead(@Argument Long userId){
        return userService.getUserById(userId);
    }


}
