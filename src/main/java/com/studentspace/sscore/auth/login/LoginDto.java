package com.studentspace.sscore.auth.login;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    @GraphQLQuery
    private String username;
    @GraphQLQuery
    private String password;
}
