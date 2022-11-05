package com.cdn.auth.auth.mvc.controller;
import com.cdn.auth.auth.db.service.UserService;
import com.cdn.auth.auth.mvc.model.TokenResponse;
import com.cdn.auth.auth.mvc.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.cdn.auth.auth.mvc.converter.TokenConverter.toResponse;

@RestController
@RequestMapping("/token")
public class Token {

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public ResponseEntity<TokenResponse> addToken(@RequestBody UserRequest userRequest) {
        return toResponse(userService.addToken(userRequest));
    }
}
