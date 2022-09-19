package com.cdn.auth.auth.mvc.controller;

import com.cdn.auth.auth.db.service.TokenService;
import com.cdn.auth.auth.mvc.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cdn.auth.auth.mvc.converter.MessageConverter.getStatus;

@RestController
@RequestMapping("/${auth.path}")
public class Auth {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/**")
    public ResponseEntity verification(@RequestBody UserRequest userRequest) {
        return getStatus(tokenService.validateToken(userRequest)? HttpStatus.OK: HttpStatus.UNAUTHORIZED);
    }
}
