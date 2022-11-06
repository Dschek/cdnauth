package com.cdn.auth.auth.mvc.controller;

import com.cdn.auth.auth.db.service.Service;
import com.cdn.auth.auth.mvc.converter.MessageConverter;
import com.cdn.auth.auth.mvc.model.StatusResponse;
import com.cdn.auth.auth.mvc.model.TokenResponse;
import com.cdn.auth.auth.mvc.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cdn.auth.auth.mvc.converter.MessageConverter.getStatus;
import static com.cdn.auth.auth.mvc.converter.TokenConverter.toResponse;

@RestController
@RequestMapping("/")
public class Auth {

    @Autowired
    private Service service;

    @PostMapping("/user/add")
    public ResponseEntity<StatusResponse> registration(@RequestBody UserRequest userRequest) {
        return MessageConverter.getMessage(service.registration(userRequest));
    }

    @PostMapping("/token/add")
    public ResponseEntity<TokenResponse> addToken(@RequestBody UserRequest userRequest) {
        return toResponse(service.addToken(userRequest));
    }

    @GetMapping("/**")
    public ResponseEntity verification(@RequestParam String token, @RequestParam String login) {
        return getStatus(service.validateToken(token, login)? HttpStatus.OK: HttpStatus.UNAUTHORIZED);
    }
}