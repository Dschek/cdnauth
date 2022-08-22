package com.cdn.auth.auth.mvc.converter;

import com.cdn.auth.auth.db.model.Token;
import com.cdn.auth.auth.mvc.model.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TokenConverter {
    public static ResponseEntity<TokenResponse> toResponse(String token){
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
