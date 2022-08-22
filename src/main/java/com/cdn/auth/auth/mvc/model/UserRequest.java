package com.cdn.auth.auth.mvc.model;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String token;
    private String password;
}
