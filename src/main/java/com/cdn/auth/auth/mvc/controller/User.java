package com.cdn.auth.auth.mvc.controller;

import com.cdn.auth.auth.db.service.TokenService;
import com.cdn.auth.auth.db.service.UserService;
import com.cdn.auth.auth.mvc.converter.MessageConverter;
import com.cdn.auth.auth.mvc.model.StatusResponse;
import com.cdn.auth.auth.mvc.model.UserRequest;
import com.cdn.auth.auth.mvc.model.UserResponse;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${api.path}/user")
public class User {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ResponseEntity<StatusResponse> registration(@RequestBody UserRequest userRequest) {
        return MessageConverter.getMessage(userService.registration(userRequest));
    }
}
