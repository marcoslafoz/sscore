package com.studentspace.sscore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class UserQuery {

    @Autowired
    private UserService userService;

    @MutationMapping
    public User createUser(@Argument User user) {
        boolean isCreated = userService.save(user);
        if (isCreated) return user;
        
        return null;
    }

    @QueryMapping
    public User getUserById(@Argument Integer id){
        return userService.getUserById(id);
    }


}
