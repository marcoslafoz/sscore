package com.studentspace.sscore.user;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
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
public class UserQuery {

    @Autowired
    private UserService userService;

    @GraphQLMutation(name = "create_user")
    @MutationMapping
    public User createUser(@Argument User user) {
        boolean isCreated = userService.save(user);
        if (isCreated) return user;
        
        return null;
    }

    @QueryMapping
    public User getUserById(@Argument Long id){
        return userService.getUserById(id);
    }


}
