package com.studentspace.sscore.auth.context;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContextDto {
    private boolean isAuthenticated;
    private Long userID;
}
