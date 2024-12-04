package com.studentspace.sscore.domain.user;

import com.studentspace.sscore.security.JwtService;
import com.studentspace.sscore.security.PasswordEncryption;
import graphql.GraphQLException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncryption passwordEncryption;

    @MutationMapping
    public String userCreate(@Argument User user) {

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        if(user.getBirthday() != null ) newUser.setBirthday(Date.valueOf(user.getBirthday().toLocalDate()));
        boolean isCreated = userService.save(newUser);

        if (isCreated) {
            Optional<User> optionalCreatedUser = userService.getUserByUsername(user.getUsername());
            if (optionalCreatedUser.isPresent()) {
                User createdUser = optionalCreatedUser.get();
                return jwtService.generateToken(createdUser);
            }
        }
        throw new GraphQLException();
    }

    @QueryMapping
    public User userRead(@Argument Long userId) {
        return userService.load(userId);
    }

    @QueryMapping
    public List<Avatar> avatarGetList() {
        return userService.getAvatarList();
    }

    @MutationMapping
    public boolean userEdit(@Argument User user){

        User editedUser = userService.load(user.getId());
        editedUser.setName(user.getName());
        editedUser.setSurname(user.getSurname());
        if(user.getBirthday() != null) editedUser.setBirthday(Date.valueOf(user.getBirthday().toLocalDate()));

        return true;
    }

    @MutationMapping
    public boolean userChangePassword(@Argument Long userId, @Argument String password){

        User editedUser = userService.load(userId);

        String encryptedPassword = passwordEncryption.encryptPassword(password);

        editedUser.setPassword(encryptedPassword);
        userService.update(editedUser);

        return true;
    }

    @MutationMapping
    public boolean userChangeAvatar(@Argument Long userId, @Argument Long avatarId){

        User editedUser = userService.load(userId);

        Avatar avatar = new Avatar();
        avatar.setId(avatarId);
        editedUser.setAvatar(avatar);

        return true;

    }

}
