package com.cdn.auth.auth.mvc.converter;

import com.cdn.auth.auth.mvc.model.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageConverter {
    public static ResponseEntity<StatusResponse> getMessage(boolean isTrue) {
        return new ResponseEntity<>(new StatusResponse(isTrue ? 1 : 0), HttpStatus.OK);
    }

    public static ResponseEntity<StatusResponse> getMessage(int status) {
        return new ResponseEntity<>(new StatusResponse(status), HttpStatus.OK);
    }

    public static ResponseEntity<StatusResponse> getStatus(HttpStatus status) {

        return new ResponseEntity<>(new StatusResponse(1), status);
    }
}